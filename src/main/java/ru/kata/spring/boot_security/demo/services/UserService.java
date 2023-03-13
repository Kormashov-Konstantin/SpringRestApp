package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.User;

import java.util.Set;

public interface UserService {
    void saveUser(User user);

    Set<User> getAllUsersSet();

    User findById(long id);

    User findByUsername(String username);

    void updateUser(User user);

    void deleteUser(Long id);

    void truncateTable();

}
