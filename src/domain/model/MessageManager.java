package domain.model;

import domain.Interfaces.IMessageManager;

import java.rmi.server.UID;
import java.util.*;

/**
 * A class for managing messages in a conversation.
 */
public class MessageManager implements IMessageManager {

    protected Conversation _managedChat;

    /**
     * Creates a new message manager for the given conversation.
     *
     * @param conversation the conversation to manage
     */
    public MessageManager(Conversation conversation) {
        _managedChat = conversation;
    }

    /**
     * Adds a message to the conversation.
     *
     * @param m the message to add
     */
    public void addMessage(Message<?> m) {
        _managedChat.getMessages().add(m);
    }

    /**
     * Removes the message with the given ID from the conversation.
     *
     * @param id the ID of the message to remove
     */
    public void removeMessageById(UID id) {
        _managedChat.getMessages().removeIf(msg -> Objects.equals(msg._id, id));
    }

    /**
     * Gets an unmodifiable list of all messages in the conversation.
     *
     * @return the list of messages
     */
    @Override
    public List<Message<?>> getMessages() {
        return Collections.unmodifiableList(_managedChat.messages);
    }

    /**
     * Gets the message with the given ID, if it exists in the conversation.
     *
     * @param uid the ID of the message to get
     * @return an Optional containing the message, or an empty Optional if the message is not found
     */
    @Override
    public Optional<Message<?>> getMessageById(UID uid) {
        return _managedChat.messages.stream().filter(msg -> msg._id.equals(uid)).findFirst();
    }

    /**
     * Gets the newest message in the conversation, if one exists.
     *
     * @return an Optional containing the newest message, or an empty Optional if the conversation has no messages
     */
    @Override
    public Optional<Message<?>> getNewestMessage() {
        return Optional.ofNullable(_managedChat.messages.get(_managedChat.messages.size() - 1));
    }
    @Override
    public List<Message<?>> getLastMessages(int count) {
        int totalMessages = _managedChat.messages.size();
        if (count > totalMessages) {
            count = totalMessages;
        }
        return new ArrayList<>(_managedChat.messages.subList(totalMessages - count, totalMessages));
    }
    /**
     * Removes all messages from the conversation.
     */
    public void clearChat() {
        _managedChat.messages.clear();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + _managedChat.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GroupChat)) return false;
        return this._managedChat.equals(obj);
    }

}