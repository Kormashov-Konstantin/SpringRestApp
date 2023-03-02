package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User showUserById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void removeUserById(Long id);

}
