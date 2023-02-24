package domain.Interfaces;

import domain.model.GroupChat;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IGroupChatRepository {
    void add(GroupChat channel);
    void delete(GroupChat channel);
    public Optional<GroupChat> findById(UID id);
    public List<GroupChat> findAll();
    public void update(GroupChat channel);

}
