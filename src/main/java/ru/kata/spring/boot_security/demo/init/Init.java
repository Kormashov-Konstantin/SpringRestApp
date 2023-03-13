package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Set;

@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        String adminRole = "ROLE_ADMIN";
        String userRole = "ROLE_USER";
        Role admin = new Role(adminRole);
        Role user = new Role(userRole);

        roleService.save(admin);
        roleService.save(user);

        Role savedAdmin = roleService.findRoleByName(adminRole);
        Role savedUser = roleService.findRoleByName(userRole);

        User first = new User();

        first.setUsername("admin@mail.ru");
        first.setPassword("admin");
        first.setAge(23);
        first.setFirstName("admin");
        first.setLastName("admiLastName");
        first.setRoles(Set.of(savedAdmin, savedUser));
        userService.saveUser(first);


        User second = new User();
        second.setUsername("user@mail.ru");
        second.setPassword("user");
        second.setAge(28);
        second.setFirstName("user");
        second.setLastName("UserLastName");
        second.setRoles(Set.of(savedUser));
        userService.saveUser(second);
    }

    @PreDestroy
    void preDestroy() {
        userService.truncateTable();
        roleService.truncateTable();
    }
}
