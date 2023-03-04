package domain.repository;

import domain.Interfaces.IChannelRepository;
import domain.model.Channel;

import java.rmi.server.UID;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelRepository implements IChannelRepository {
    private final ConcurrentHashMap<UID, Channel> channels;

    public ChannelRepository() {
        this.channels = new ConcurrentHashMap<>();
    }

    public void add(Channel channel) {
        channels.put(channel.get_id(), channel);
    }

    @Override
    public Optional<Channel> findById(UID id) {
        return Optional.ofNullable(channels.get(id));
    }

    @Override
    public List<Channel> findAll() {
        return new ArrayList<>(channels.values());
    }
    @Override
    public void update(Channel channel) {
        channels.put(channel.get_id(), channel);
    }

    @Override
    public void delete(Channel channel) {
        channels.remove(channel.get_id());
    }
}
