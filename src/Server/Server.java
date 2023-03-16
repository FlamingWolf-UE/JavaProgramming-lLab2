package Server;

import domain.Interfaces.ICommand;
import domain.model.Commands.AuthUserCommand;
import domain.model.Commands.LoadUserDataCommand;
import domain.model.Commands.SendMessageToGroupChatCommand;
import domain.model.GroupChat;
import domain.model.User;
import domain.repository.GroupChatRepository;
import domain.repository.UserRepository;
import service.GroupChatService;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server
{
    private final Integer _port;
    private final ExecutorService _executorService;
    private final GroupChatService _groupChatService;

    private final UserService _userService;
    private final List<ClientHandler> _handlers;

    public Server(Integer port, UserService us, GroupChatService gcs) {
        _port = port;
        _userService = us;
        _executorService = Executors.newCachedThreadPool();
        _groupChatService = gcs;
        _handlers = new ArrayList<>();
    }

    public static void main(String[] args) {

        UserService us = new UserService(new UserRepository());
        var user1 = new User("User1", "password", new ArrayList<>());
        var user2 = new User("User2", "password", new ArrayList<>());
        var user3 = new User("User3", "password", new ArrayList<>());
        us.addUser(user1);
        us.addUser(user2);
        us.addUser(user3);



        GroupChatService gcs = new GroupChatService(new GroupChatRepository());
        var chat = new GroupChat("My own chat");
        var chat2 = new GroupChat("My new own chat");
        gcs.createGroupChat(chat);
        gcs.createGroupChat(chat2);
        gcs.addParticipantToGroupChat(chat.getId(), user1);
        gcs.addParticipantToGroupChat(chat.getId(), user2);
        gcs.addParticipantToGroupChat(chat2.getId(), user2);
        gcs.addParticipantToGroupChat(chat.getId(), user3);
        gcs.addParticipantToGroupChat(chat2.getId(), user3);
        user1.AddChatUID(chat.getId());
        user2.AddChatUID(chat.getId());
        user2.AddChatUID(chat2.getId());
        user3.AddChatUID(chat.getId());
        user3.AddChatUID(chat2.getId());
        Server server = new Server(12345, us, gcs);

        server.start();
    }
 
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(_port))
        {

            System.out.println("Server started on the port " + _port);
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                _handlers.add(clientHandler);
                _executorService.submit(clientHandler);
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Server stopped");
            _executorService.shutdown();
        }
    }

    public class ClientHandler implements Runnable {

        private User user;
        private final Socket clientSocket;
        private Boolean needToClose;
        private Boolean isAuthentificated;
        private final ObjectOutputStream oos;
        private final ObjectInputStream ois;

        public ClientHandler(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            needToClose = false;
            isAuthentificated = false;
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
        }

        public User getUser() {
            return user;
        }

        @Override
        public void run() {

            while (!needToClose) try{

                ICommand command = (ICommand)(ois.readObject());
                if (!isAuthentificated){
                    if (command instanceof AuthUserCommand)
                {
                    var request =  ((AuthUserCommand) command).getRequest();
                    var user = _userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
                    if (user.isPresent())
                    {
                        var response = new LoadUserDataCommand();
                        response.setUser(user.get());

                        for (var chatId:user.get().get_chatUIDs()) {
                           var chat = _groupChatService.findGroupChatById(chatId);
                            chat.ifPresent(response::addChat);
                        }
                        oos.writeObject(response);
                        this.user = user.get();
                        this.isAuthentificated = true;
                    }
                }}
                else {
                if (command instanceof SendMessageToGroupChatCommand request)
                {
                    _groupChatService.addMessageToGroupChat(request.getGroupChatId(), request.getMessage());
                    for (ClientHandler client:_handlers) {
                        User currentUser = client.getUser();
                        if (currentUser.get_id() != user.get_id() && currentUser.get_chatUIDs().contains(request.getGroupChatId())) {
                            client.oos.writeObject(request);
                        }
                    }
                }


            }

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Handler will aborted");
                try {
                    this.needToClose = true;
                    clientSocket.close();
                    _handlers.remove(this);
                    System.out.println("Handler aborted");
                    System.out.println("Clients count now: " + _handlers.size());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Handler aborted error");
                }
            }

        }


    }
}
