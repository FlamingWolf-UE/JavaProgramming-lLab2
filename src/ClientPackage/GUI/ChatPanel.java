package ClientPackage.GUI;

import domain.model.Message;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    public final JPanel messagesPanel;

    public ChatPanel() {
        setLayout(new BorderLayout());

        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        JScrollPane messagesScrollPane = new JScrollPane(messagesPanel);
        messagesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(messagesScrollPane, BorderLayout.CENTER);

    }

    public void addMessage(Message<?> message)
    {
        MessageBlock messageBlock = new MessageBlock(message.get_senderId().toString(), message.get_content().toString(), message.get_timestamp());
        messageBlock.setMaximumSize(new Dimension(Integer.MAX_VALUE, messageBlock.getPreferredSize().height));
        messagesPanel.add(messageBlock);
        messagesPanel.revalidate();
        messagesPanel.repaint();
    }

}