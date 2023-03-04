package domain.repository;

import domain.Interfaces.IPrivateChatRepository;
import domain.model.PrivateChat;
import domain.model.User;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PrivateChatRepository implements IPrivateChatRepository {
    private ConcurrentHashMap<UID, PrivateChat> privateChatHashMap;

    public PrivateChatRepository() {
        this.privateChatHashMap = new ConcurrentHashMap<>();
    }

    @Override
    public void add(PrivateChat privateChat) {
        privateChatHashMap.put(privateChat.getId(), privateChat);
    }

    @Override
    public void delete(PrivateChat privateChat) {
        privateChatHashMap.remove(privateChat.getId());
    }

    @Override
    public Optional<PrivateChat> findById(UID id) {
        return Optional.ofNullable(privateChatHashMap.get(id));
    }

    @Override
    public List<PrivateChat> findAll() {
        return new ArrayList<>(privateChatHashMap.values());
    }

    @Override
    public Optional<PrivateChat> findByParticipants(User user1, User user2) {
        for (PrivateChat privateChat : privateChatHashMap.values()) {
            if (privateChat.getParticipants().contains(user1) && privateChat.getParticipants().contains(user2)) {
                return Optional.of(privateChat);

            }
        }
        return Optional.empty();
    }

    @Override
    public void update(PrivateChat privateChat) {
        privateChatHashMap.replace(privateChat.getId(), privateChat);
    }
}
