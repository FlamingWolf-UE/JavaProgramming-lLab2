package domain.model;

import domain.Interfaces.UserManagable;

import java.rmi.server.UID;
import java.util.Optional;

/**
 * Represents a group chat, which is a type of conversation that allows multiple participants.
 */
public class GroupChat extends Conversation implements UserManagable {

    private String _name;

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Constructs a new GroupChat object with the specified ID and name.
     *
     * @param name The name of the group chat.
     */
    public GroupChat(String name) {
        super(new UID());
        _name = name;
    }

    @Override
    public UID getId() {

        return super.getId();
    }

    /**
     * Removes the specified user from the group chat's list of participants.
     *
     * @param user The user to remove from the group chat.
     */
    @Override
    public void removeParticipant(User user) {
        participants.remove(user);
    }

    /**
     * Adds the specified user to the group chat's list of participants.
     *
     * @param user The user to add to the group chat.
     */
    @Override
    public void addParticipant(User user) {
        participants.add(user);
    }

    /**
     * Returns the user with the specified ID if they are a participant in the group chat.
     *
     * @param uid The ID of the user to search for.
     * @return An Optional containing the user if they are a participant in the group chat, or an empty Optional otherwise.
     */
    @Override
    public Optional<User> getParticipantById(UID uid) {
        return participants.stream().filter(user -> user.get_id().equals(uid)).findFirst();
    }


    /**
     * Returns a hash code for the group chat.
     *
     * @return A hash code for the group chat.
     */
    @Override
    public int hashCode() {
        var result = super.hashCode();
        return result + 31 * _name.hashCode();
    }

    /**
     * Compares this group chat to the specified object for equality.
     *
     * @param obj The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GroupChat other)) return false;
        return this._id.equals(other._id);
    }

    /**
     * Returns a string representation of the group chat.
     *
     * @return A string representation of the group chat.
     */
    @Override
    public String toString() {
        return "GroupChat{" +
                "_name='" + _name + '\'' +
                ", _id=" + _id.toString() +
                '}';
    }
}