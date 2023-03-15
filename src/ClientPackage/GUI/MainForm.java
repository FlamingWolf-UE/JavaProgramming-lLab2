package ClientPackage.GUI;

import ClientPackage.Client;
import ClientPackage.Interfaces.ServerListener;
import ClientPackage.domain.ChatsRepository;
import domain.model.Commands.LoadUserDataCommand;
import domain.model.Commands.SendMessageToGroupChatCommand;
import domain.model.GroupChat;
import domain.model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class MainForm extends JFrame implements ActionListener, ServerListener {

    ChatsRepository repository;
    Client client;

    JTextArea textArea;
    JTextField inputField;

    DefaultListModel<GroupChat> model;
    JList<GroupChat> chatList;
    JButton sendButton;
    ChatPanel rightPanel;
    public MainForm() throws IOException, ClassNotFoundException {
        repository = new ChatsRepository();
        client = new Client(this, "localhost", 12345);
        client.connect();
        client.startListeningServer();
        client.initMessageSending();
        System.out.println("Authentificate :");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        String password = scanner.nextLine();
        client.authenticate(user, password);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Чат");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel leftPanel = new JPanel(new BorderLayout());
        model = new DefaultListModel<>();
        chatList = new JList<>(model);
        chatList.setCellRenderer(new ChatListCellRenderer());
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        chatList.addListSelectionListener(e -> {
            rightPanel.messagesPanel.removeAll();
            var list = chatList.getSelectedValue().get_msgManager().getLastMessages(10);
            for (var message:list) {
                rightPanel.addMessage(message);
            }
            revalidate();
            repaint();
        });
        JScrollPane scrollPane2 = new JScrollPane(chatList);
        leftPanel.add(scrollPane2, BorderLayout.CENTER);
        leftPanel.setPreferredSize(new Dimension(150, getHeight())); // Задаем размер панели
        rightPanel = new ChatPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        setSize(600, 400); // Задаем размер окна
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);


        inputField = new JTextField();

         sendButton = new JButton("Отправить");
        sendButton.addActionListener(e -> sendMessage());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);


        add(inputPanel, BorderLayout.SOUTH);
        setVisible(true);
        repaint();
    }

    private void sendMessage() {
        String message = inputField.getText();
        inputField.setText("");

        MessageBlock messageBlock = new MessageBlock(client.getAuthUser().get_name(), message, new Date());
        messageBlock.setMaximumSize(new Dimension(Integer.MAX_VALUE, messageBlock.getPreferredSize().height));
        rightPanel.messagesPanel.add(messageBlock);
        rightPanel.messagesPanel.revalidate();
        rightPanel.messagesPanel.repaint();
        var msg = new Message<>(client.getAuthUser().get_id(), new Date(), message);
        repository.getChatById(chatList.getSelectedValue().getId()).get().get_msgManager().addMessage(msg);
        client.sendMessage(chatList.getSelectedValue().getId(), msg);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new MainForm();
    }



    @Override
    public void onMessageRecieved(SendMessageToGroupChatCommand command) {
        var chat =     repository.getChatById(command.getGroupChatId());
        chat.ifPresent(groupChat -> groupChat.get_msgManager().addMessage(command.getMessage()));
       if  (chatList.getSelectedValue().getId().equals(command.getGroupChatId()))
       {
           rightPanel.addMessage(command.getMessage());
           revalidate();
           repaint();
       }


    }

    @Override
    public void onAuthentificate(LoadUserDataCommand command) {


        repository.setChats( command.getChats());
        repository.getChats().forEach(chat -> model.addElement(chat));
        revalidate();
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}