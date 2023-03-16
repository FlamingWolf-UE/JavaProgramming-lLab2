package ClientPackage.GUI;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageBlock extends JPanel {
    private JLabel nameLabel;
    private JLabel messageLabel;
    private JLabel timeLabel;
    public MessageBlock(String name, String message, Date date) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        nameLabel = new JLabel(name + ":");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        messageLabel = new JLabel(message);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        timeLabel = new JLabel(new SimpleDateFormat("hh:mm").format(date));
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(nameLabel, BorderLayout.NORTH);
        leftPanel.add(messageLabel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.CENTER);
        add(timeLabel, BorderLayout.EAST);
    }
}