
package domain.repository;

import domain.Interfaces.IGroupChatRepository;
import domain.model.GroupChat;

import java.rmi.server.UID;
import java.util.*;

public class GroupChatRepository implements IGroupChatRepository {

    private final Map<UID, GroupChat> groupChats;

    public GroupChatRepository() {
        groupChats = new HashMap<>();
    }

    /**
     * Adds the specified group chat to the repository.
     *
     * @param groupChat The group chat to add.
     */
    @Override
    public void add(GroupChat groupChat) {
        groupChats.put(groupChat.getId(), groupChat);
    }

    /**
     * Deletes the specified group chat from the repository.
     *
     * @param groupChat The group chat to delete.
     */
    @Override
    public void delete(GroupChat groupChat) {
        groupChats.remove(groupChat.getId());
    }

    /**
     * Finds a group chat by its unique identifier.
     *
     * @param id The unique identifier of the group chat to find.
     * @return An Optional containing the found group chat, or an empty Optional if the group chat was not found.
     */
    @Override
    public Optional<GroupChat> findById(UID id) {
        return Optional.ofNullable(groupChats.get(id));
    }

    /**
     * Returns a list of all group chats in the repository.
     *
     * @return A list of all group chats in the repository.
     */
    @Override
    public List<GroupChat> findAll() {
        return new ArrayList<>(groupChats.values());
    }

    /**
     * Updates the specified group chat in the repository.
     *
     * @param groupChat The group chat to update.
     */
    @Override
    public void update(GroupChat groupChat) {
        groupChats.put(groupChat.getId(), groupChat);
    }
}
