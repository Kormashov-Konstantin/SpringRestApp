package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.Set;

public interface RoleService {
    Set<Role> getRolesSet();

    void save(Role role);

    Role findRoleByName(String name);

    void truncateTable();

    void setUserRoles(User user);


}
