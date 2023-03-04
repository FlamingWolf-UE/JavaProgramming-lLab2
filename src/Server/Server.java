package Server;

import domain.repository.GroupChatRepository;
import service.GroupChatService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server{
    private final Integer _port;
    private final ExecutorService _executorService;
    private final GroupChatService _groupChatService;
    public Server(Integer port) {
        _port = port;
        _executorService = Executors.newCachedThreadPool();
        _groupChatService = new GroupChatService(new GroupChatRepository());

    }

    public static void main(String[] args) {
        Server server = new Server(12345);
        server.start();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(_port))
        {

            System.out.println("Server started on the port " + _port);
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, _groupChatService);
                _executorService.submit(clientHandler);
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("Server stopped");
        }
    }
}
