package domain.Interfaces;

import domain.model.Channel;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IChannelRepository {
    void add(Channel channel);
    void delete(Channel channel);
    public Optional<Channel> findById(UID id);
    public List<Channel> findAll();
    public void update(Channel channel);
}
