package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.util.*;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        return repository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<String> readAll() {
        return repository.readAll();
    }


    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }


    public boolean deleteUser(Long userId) {
    return repository.delete(userId);
    }
}
