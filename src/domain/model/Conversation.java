package domain.model;


import domain.Interfaces.IMessageManager;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;

/**

 Abstract class that represents a conversation, which is a discussion between users in a messaging system.
 */
public abstract class Conversation {
    protected IMessageManager _msgManager;
    protected List<User> participants = new ArrayList<>();
    protected UID _id;
    protected List<Message<?>> messages = new ArrayList<>();
    /**
     * Returns the list of messages in the conversation.
     *
     * @return a list of messages in the conversation.
     */
    List<Message<?>> getMessages() {
        return messages;
    }

    /**
     * Constructor for the conversation class.
     *
     * @param id unique ID of the conversation.
     */
    Conversation(UID id)
    {
        _id = id;
        _msgManager = new MessageManager(this);
    }

    /**
     * Returns the message manager for the conversation.
     *
     * @return the message manager for the conversation.
     */
    public IMessageManager get_msgManager() {
        return _msgManager;
    }

    /**
     * Returns the ID of the conversation.
     *
     * @return the ID of the conversation.
     */
    public UID getId() {
        return _id;
    }

    /**
     * Compares the current object to the provided object for equality.
     *
     * @param o the object to compare for equality.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Conversation)) return false;
        Conversation that = (Conversation) o;
        return _id.equals(that._id);
    }

    /**
     * Returns the hash code for the current object.
     *
     * @return the hash code for the current object.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result += prime * result + ((_id == null) ? 0 : _id.hashCode());
        result += prime * result + ((_msgManager == null) ? 0 : _msgManager.hashCode());
        result += prime * result + ((messages == null) ? 0 : messages.hashCode());
        result += prime * result + ((participants == null) ? 0 : participants.hashCode());
        return result;
    }

    /**
     * Returns a string representation of the current object.
     *
     * @return a string representation of the current object.
     */
    @Override
    public String toString() {
        return "Conversation [_msgManager=" + _msgManager.toString() + ", participants=" + participants.toString() + ", _id=" + _id.toString()
                + ", messages=" + messages.toString() + "]";
    }
}



