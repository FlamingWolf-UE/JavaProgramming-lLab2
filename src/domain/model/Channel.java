package domain.model;

import domain.Interfaces.UserManagable;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A class that represents a Channel in a chat application.
 * This class implements the `UserManagable` interface, which provides
 * functionality for adding, removing, and retrieving users from a group.
 */
public class Channel implements UserManagable {
    protected UID _id;
    protected String _name;
    protected List<GroupChat> _chats;
    protected List<User> _users;

    /**
     * Constructs a `Channel` object with the specified `id` and `name`.
     * @param id the unique identifier for this `Channel`.
     * @param name the name of this `Channel`.
     */
    public Channel(UID id, String name)
    {
        _id = id;
        _name = name;
        _users = new ArrayList<>();
        _chats = new ArrayList<>();
    }


    /**
     * Returns the unique identifier of this `Channel`.
     * @return the unique identifier of this `Channel`.
     */
    public UID get_id() {
        return _id;
    }

    /**
     * Adds a `GroupChat` object to this `Channel`.
     * @param chat the `GroupChat` object to add to this `Channel`.
     */
    public synchronized void addGroupChat(GroupChat chat)
    {
        _chats.add(chat);
    }

    public synchronized String get_name()
    {
        return _name;
    }


    /**
     * Returns an unmodifiable list of `GroupChat` objects associated with this `Channel`.
     * @return an unmodifiable list of `GroupChat` objects associated with this `Channel`.
     */
    public synchronized List<GroupChat> getAllChats()
    {
        return Collections.unmodifiableList(_chats);
    }

    /**
     * Returns an `Optional` object that contains a `GroupChat` object associated with this `Channel`,
     * if one exists with the specified `uid`.
     * @param uid the unique identifier of the `GroupChat` object to retrieve.
     * @return an `Optional` object that contains a `GroupChat` object associated with this `Channel`,
     *         if one exists with the specified `uid`.
     */
    public synchronized Optional<GroupChat> getGroupChatById(UID uid)
    {
        return _chats.stream().filter(chat -> chat._id.equals(uid)).findFirst();
    }

    /**
     * Removes a `GroupChat` object from this `Channel`.
     * @param chat the `GroupChat` object to remove from this `Channel`.
     */
    public synchronized void removeChat(GroupChat chat)
    {
        _chats.remove(chat);
    }

    @Override
    public synchronized void removeParticipant(User user) {
        _users.remove(user);
    }

    @Override
    public synchronized void addParticipant(User user) {
        _users.add(user);
    }

    @Override
    public synchronized Optional<User> getParticipantById(UID uid) {
        return _users.stream().filter(user -> user.get_id().equals(uid)).findFirst();
    }

    @Override
    public synchronized List<User> getParticipants() {
        return Collections.unmodifiableList(_users);
    }
}
