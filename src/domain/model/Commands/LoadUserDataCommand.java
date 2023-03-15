package domain.model.Commands;

import domain.Interfaces.ICommand;
import domain.model.GroupChat;
import domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoadUserDataCommand implements ICommand {

    private List<GroupChat> chats;
    private User user;

    public List<GroupChat> getChats() {
        return chats;
    }

    public void addChat(GroupChat chat)
    {
        chats.add(chat);
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setChats(List<GroupChat> chats) {
        this.chats = chats;
    }

    public LoadUserDataCommand()
    {
        chats = new ArrayList<>();
    }

    @Override
    public void execute() {

    }
}
