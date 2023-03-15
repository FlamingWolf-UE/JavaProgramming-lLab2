package service;

import domain.Interfaces.IGroupChatRepository;
import domain.Interfaces.IGroupChatService;
import domain.model.GroupChat;
import domain.model.Message;
import domain.model.User;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupChatService implements IGroupChatService {
    private final IGroupChatRepository chatRepository;

    public GroupChatService(IGroupChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public void createGroupChat(GroupChat chat) {
        chatRepository.add(chat);
    }

    @Override
    public void deleteGroupChat(GroupChat chat) {
        chatRepository.delete(chat);
    }

    @Override
    public Optional<GroupChat> findGroupChatById(UID id) {
        return chatRepository.findById(id);
    }

    @Override
    public List<GroupChat> findAllGroupChats() {
        return chatRepository.findAll();
    }

    @Override
    public void updateGroupChat(GroupChat chat) {
        chatRepository.update(chat);
    }

    @Override
    public void addMessageToGroupChat(UID groupId, Message<?> msg) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(groupChat -> groupChat.get_msgManager().addMessage(msg));
    }

    @Override
    public void deleteMessageFromGroupChatById(UID groupId, UID msgId) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(groupChat -> groupChat.get_msgManager().removeMessageById(msgId));
    }

    @Override
    public void clearGroupChat(UID groupId) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(groupChat -> groupChat.get_msgManager().clearChat());
    }

    @Override
    public Optional<Message<?>> getLatestMessage(UID groupId) {
        var chat = chatRepository.findById(groupId);
        if (chat.isPresent()){
            return chat.get().get_msgManager().getNewestMessage();
        }
        return Optional.empty();
    }

    @Override
    public List<Message<?>> getLastMessagesOfGroupChat(UID groupId, int count) {
        var chat = chatRepository.findById(groupId);
        if (chat.isPresent()){
            return chat.get().get_msgManager().getLastMessages(count);
        }
        return new ArrayList<>();
    }

    @Override
    public void addParticipantToGroupChat(UID groupId, User user) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(value -> value.addParticipant(user));
    }

    @Override
    public void removeParticipantFromGroupChat(UID groupId, User user) {
        var channel = chatRepository.findById(groupId);
        channel.ifPresent(value -> value.removeParticipant(user));
    }


}
