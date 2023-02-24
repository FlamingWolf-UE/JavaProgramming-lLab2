package service;

import domain.Interfaces.IUserRepository;
import domain.Interfaces.IUserService;
import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.add(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.update(user);
    }

    @Override
    public Optional<User> findUserById(UID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
