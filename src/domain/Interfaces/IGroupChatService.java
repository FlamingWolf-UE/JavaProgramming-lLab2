package domain.Interfaces;

import domain.model.GroupChat;
import domain.model.Message;
import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IGroupChatService {
    void createGroupChat(GroupChat chat);
    void deleteGroupChat(GroupChat chat);
    Optional<GroupChat> findGroupChatById(UID id);
    List<GroupChat> findAllGroupChats();
    void updateGroupChat(GroupChat chat);
    public void addMessageToGroupChat(UID groupId, Message<?> msg);
    public void deleteMessageFromGroupChatById(UID groupId, UID msgId);
    public void clearGroupChat(UID groupId);
    public Optional<Message<?>> getLatestMessage(UID groupId);
    public List<Message<?>> getLastMessagesOfGroupChat(UID groupId, int count);
    public void addParticipantToGroupChat(UID groupId, User user);
    public void removeParticipantFromGroupChat(UID groupId, User user);
}
