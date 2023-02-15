
package Interfaces;
import Classes.Message;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

/**

 An interface representing a message manager that can add, remove, retrieve, and manipulate messages.
 */
public interface IMessageManager {
    /**
     * Add a new message to the message manager.
     * @param msg The message to add.
     */
    void addMessage(Message<?> msg);

    /**
     * Remove a message from the message manager by its unique ID.
     * @param id The ID of the message to remove.
     */
    void removeMessageById(UID id);

    /**
     * Get a list of all messages stored in the message manager.
     * @return A list of all messages in the message manager.
     */
    List<Message<?>> getMessages();

    /**
     * Get a message from the message manager by its unique ID.
     * @param uid The ID of the message to retrieve.
     * @return An optional containing the retrieved message, or an empty optional if the message doesn't exist.
     */
    Optional<Message<?>> getMessageById(UID uid);

    /**
     * Get the newest message stored in the message manager.
     * @return An optional containing the newest message, or an empty optional if no messages exist.
     */
    Optional<Message<?>> getNewestMessage();

    /**
     * Clear all messages stored in the message manager.
     */
    void clearChat();
}