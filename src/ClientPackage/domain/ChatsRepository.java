package ClientPackage.domain;

import domain.model.GroupChat;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public class ChatsRepository {
    private List<GroupChat> chats;


        public Optional<GroupChat> getChatById(UID chatId) {
        for (GroupChat chat : chats) {
            if (chat.getId().equals(chatId)) {
                return Optional.of(chat);
            }
        }
        return Optional.empty();
    }

    public List<GroupChat> getChats() {
        return chats;
    }

    public void setChats(List<GroupChat> chats) {
        this.chats = chats;
    }

    public void loadChat(GroupChat chat)
    {
        chats.add(chat);
    }




}
