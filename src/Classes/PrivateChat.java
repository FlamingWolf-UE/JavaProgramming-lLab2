package Classes;

import java.rmi.server.UID;

/**
 * A class that represents a private chat between two users.
 */
public class PrivateChat extends Conversation {

    /**
     * Creates a new private chat with two users.
     * @param user1 the first user
     * @param user2 the second user
     */
    public PrivateChat(User user1, User user2) {
        super(new UID());
        participants.add(user1);
        participants.add(user2);
    }

    /**
     * Returns the other participant of this private chat given one participant.
     * @param primary the first participant
     * @return the other participant or null if not found
     */
    public User getOtherParticipant(User primary) {
        if (participants.get(0) == primary) {
            return participants.get(1);
        } else if (participants.get(1) == primary) {
            return participants.get(0);
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PrivateChat other)) return false;
        return this._id.equals(other._id);
    }




}