package domain.Interfaces;

import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    void addUser(User user);
    void deleteUser(User user);

    void updateUser(User user);
    Optional<User> findUserById(UID id);

    Optional<User> findUserByName(String name);

    List<User> findAllUsers();
    Optional<User> findByUsernameAndPassword(String username, String password);


}
