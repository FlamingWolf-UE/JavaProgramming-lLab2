package ClientPackage;

import ClientPackage.Interfaces.ServerListener;
import domain.model.AuthRequest;
import domain.model.Commands.AuthUserCommand;
import domain.model.Commands.LoadUserDataCommand;
import domain.model.Commands.SendMessageToGroupChatCommand;
import domain.model.Message;
import domain.model.User;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.server.UID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private  ServerListener listener;
    private final String _serverAddress;
    private final int _serverPort;
    private User _currentUser;
    private boolean _isConnected;
    private Socket _socket;
    private ObjectOutputStream _outputStream;
    private ObjectInputStream _inputStream;
    private ExecutorService _recievingExecutorService;
    private ExecutorService _sendingExecutorService;

    public Client(ServerListener listener, String serverAddress, int serverPort) {

        this.listener = listener;
        _serverAddress = serverAddress;
        _serverPort = serverPort;
    }


    public void connect() throws IOException {
        _socket = new Socket(_serverAddress, _serverPort);
        _outputStream = new ObjectOutputStream(_socket.getOutputStream());
        _inputStream = new ObjectInputStream(_socket.getInputStream());
        _isConnected = true;
        System.out.println("Connected to server.");
    }

    public void authenticate(String username, String password) throws IOException, ClassNotFoundException {
        AuthUserCommand authCommand = new AuthUserCommand();
        authCommand.setRequest(new AuthRequest(username, password));
        _outputStream.writeObject(authCommand);

    }


    public User getAuthUser()
    {
        return _currentUser;
    }


    public void sendMessage(UID chatID, Message<?> message)
    {
        if (_isConnected) _sendingExecutorService.submit(() -> {
            SendMessageToGroupChatCommand command = new SendMessageToGroupChatCommand(chatID, message);
            try {
                _outputStream.writeObject(command);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void initMessageSending()
    {
        _sendingExecutorService = Executors.newSingleThreadExecutor();
    }

    public void startListeningServer()
    {
        _recievingExecutorService = Executors.newSingleThreadExecutor();
        _recievingExecutorService.submit(() -> {
            while (_isConnected) {
                try {
                    Object obj = _inputStream.readObject();
                    if (obj instanceof SendMessageToGroupChatCommand message) {
                        SwingUtilities.invokeLater(() -> {

                            listener.onMessageRecieved(message);
                        });
                    }
                    if (obj instanceof LoadUserDataCommand message)
                    {
                        _currentUser = message.getUser();
                        if (_currentUser != null) {
                            System.out.println("Authenticated as " + _currentUser.get_name());
                        } else {
                            System.out.println("Authentication failed.");
                        }
                        SwingUtilities.invokeLater(()->
                        {
                            listener.onAuthentificate(message);
                        });

                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error receiving message: " + e.getMessage());
                    break;
                }
            }
        });

    }

    public void disconnect() throws IOException {
        _isConnected = false;
        try {
            _sendingExecutorService.shutdown();
            _recievingExecutorService.shutdown();
            _socket.close();
            System.out.println("Disconnected from server");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}