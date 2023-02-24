package domain.repository;

import domain.Interfaces.IUserRepository;
import domain.model.User;

import java.rmi.server.UID;
import java.util.*;

public class UserRepository implements IUserRepository {
    private final Map<UID, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    @Override
    public void add(User user) {
        users.put(user.get_id(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.get_id());
    }

    @Override
    public Optional<User> findById(UID id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void update(User user) {
        users.put(user.get_id(), user);
    }

    @Override
    public Optional<User> findByUsername(String name) {
        for (User user : users.values()) {
            if (user.get_name().equals(name)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
