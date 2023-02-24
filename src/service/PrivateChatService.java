package service;

import domain.Interfaces.IPrivateChatRepository;
import domain.Interfaces.IPrivateChatService;
import domain.model.Message;
import domain.model.PrivateChat;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PrivateChatService implements IPrivateChatService {

    private final IPrivateChatRepository chatRepository;

    public PrivateChatService(IPrivateChatRepository repository) {
        this.chatRepository = repository;
    }

    @Override
    public void createChat(PrivateChat chat) {
        chatRepository.add(chat);
    }

    @Override
    public void deleteChat(PrivateChat chat) {
        chatRepository.delete(chat);
    }

    @Override
    public Optional<PrivateChat> findChatById(UID id) {
        return chatRepository.findById(id);
    }

    @Override
    public List<PrivateChat> findAllChats() {
        return chatRepository.findAll();
    }

    @Override
    public void updateChat(PrivateChat chat) {
        chatRepository.update(chat);
    }

    @Override
    public void addMessageToChat(UID groupId, Message<?> msg) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(groupChat -> groupChat.get_msgManager().addMessage(msg));
    }

    @Override
    public void deleteMessageFromChatById(UID groupId, UID msgId) {
        var chat = chatRepository.findById(groupId);
        chat.ifPresent(groupChat -> groupChat.get_msgManager().removeMessageById(msgId));
    }

    @Override
    public void clearChat(UID groupId) {
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
    public List<Message<?>> getLastMessagesOfChat(UID groupId, int count) {
        var chat = chatRepository.findById(groupId);
        if (chat.isPresent()){
            return chat.get().get_msgManager().getLastMessages(count);
        }
        return new ArrayList<>();
    }
}
