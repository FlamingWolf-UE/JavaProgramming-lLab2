package domain.Interfaces;

import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    void add(User user);
    void delete(User user);
    public Optional<User> findById(UID id);
    public List<User> findAll();
    public void update(User user);
    public Optional<User> findByUsername(String name);
}
