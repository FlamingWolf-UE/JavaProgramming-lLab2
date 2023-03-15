package domain.model;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.Date;

/**

 A class representing a message in a conversation.

 @param <T> the type of the message content
 */
public class Message<T> implements Serializable {
    protected UID _id;
    protected UID _senderId;
    protected Date _timestamp;
    protected final T _content;

    /**

     Constructs a new Message object.
     @param sender the unique ID of the user who sent the message
     @param timestamp the timestamp of the message
     @param content the content of the message
     */
    public Message(UID sender, Date timestamp, T content) {
        _id = new UID();
        _senderId = sender;
        _timestamp = timestamp;
        _content = content;
    }
    /**

     Returns the timestamp of the message.
     @return the timestamp of the message
     */
    public Date get_timestamp() {
        return _timestamp;
    }
    /**

     Returns the content of the message.
     @return the content of the message
     */
    public final T get_content() {
        return _content;
    }
    /**

     Returns the unique ID of the message.
     @return the unique ID of the message
     */
    public UID get_id() {
        return _id;
    }
    /**

     Returns the unique ID of the user who sent the message.
     @return the unique ID of the user who sent the message
     */
    public UID get_senderId() {
        return _senderId;
    }
    /**

     Sets the unique ID of the message.
     @param _id the unique ID to be set
     */
    public void set_id(UID _id) {
        this._id = _id;
    }
    /**

     Sets the unique ID of the user who sent the message.
     @param _senderId the unique ID of the user to be set
     */
    public void set_senderId(UID _senderId) {
        this._senderId = _senderId;
    }
    /**

     Sets the timestamp of the message.
     @param _timestamp the timestamp to be set
     */
    public void set_timestamp(Date _timestamp) {
        this._timestamp = _timestamp;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash += 59 * hash + this._id.hashCode();
        hash += 59 * hash + this._senderId.hashCode();
        hash += 59 * hash + this._timestamp.hashCode();
        hash += 59 * hash + this._content.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message<?> other = (Message<?>) obj;
        if (!this._id.equals(other._id)) {
            return false;
        }
        if (!this._senderId.equals(other._senderId)) {
            return false;
        }
        if (!this._timestamp.equals(other._timestamp)) {
            return false;
        }
        if (!this._content.equals(other._content)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Message{" + "_id=" + _id.toString() + ", _senderId=" + _senderId.toString() + ", _timestamp=" + _timestamp.toString() + ", _content=" + _content.toString() + '}';
    }
}
