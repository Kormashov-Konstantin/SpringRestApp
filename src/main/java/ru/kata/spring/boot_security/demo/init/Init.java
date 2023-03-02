package ru.kata.spring.boot_security.demo.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Component
public class Init {
    private final UserServiceImpl userServiceImpl;
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Init(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleuser = new Role("ROLE_USER");
        Role roleadmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleadmin);
        roleRepository.save(roleuser);
        User admin = new User("admin", "admin", "admin@mail.ru", Collections.singleton(new Role(1L, "ROLE_ADMIN")));
        User user = new User("user", "user", "user@mail.ru", Collections.singleton(new Role(2L, "ROLE_USER")));
        userServiceImpl.saveUser(admin);
        userServiceImpl.saveUser(user);
    }
}
