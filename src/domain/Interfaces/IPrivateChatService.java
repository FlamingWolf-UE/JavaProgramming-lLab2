package domain.Interfaces;

import domain.model.Message;
import domain.model.PrivateChat;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IPrivateChatService {
    void createChat(PrivateChat chat);
    void deleteChat(PrivateChat chat);
    Optional<PrivateChat> findChatById(UID id);
    List<PrivateChat> findAllChats();
    void updateChat(PrivateChat chat);
    public void addMessageToChat(UID groupId, Message<?> msg);
    public void deleteMessageFromChatById(UID groupId, UID msgId);
    public void clearChat(UID groupId);
    public Optional<Message<?>> getLatestMessage(UID groupId);
    public List<Message<?>> getLastMessagesOfChat(UID groupId, int count);
}
