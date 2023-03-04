package Server;

import domain.Interfaces.ICommand;
import domain.model.Commands.SendMessageToGroupChatCommand;
import service.GroupChatService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private Boolean needToClose;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientHandler(Socket clientSocket, GroupChatService groupChatService) throws IOException {
        this.clientSocket = clientSocket;
        needToClose = false;
        oos = new ObjectOutputStream(clientSocket.getOutputStream());
        ois = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (!needToClose) try{
            ICommand command = (ICommand)ois.readObject();
            if (command instanceof SendMessageToGroupChatCommand)
            {
                //проблемы
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public void stop()
    {
        this.needToClose = true;
    }



}


