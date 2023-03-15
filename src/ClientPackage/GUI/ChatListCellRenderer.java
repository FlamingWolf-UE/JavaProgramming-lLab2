package ClientPackage.GUI;

import domain.model.GroupChat;

import javax.swing.*;
import java.awt.*;

public class ChatListCellRenderer extends JLabel implements ListCellRenderer<GroupChat> {
    public ChatListCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends GroupChat> list, GroupChat value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        setText(value.get_name()); // Отображаем имя чата
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
