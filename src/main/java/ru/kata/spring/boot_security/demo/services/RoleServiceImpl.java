package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public Set<Role> getRolesSet() {
        return new HashSet<>(rolesRepository.findAll());
    }

    @Override
    @Transactional
    public void save(Role role) {
        rolesRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return rolesRepository.findRoleByName(name);
    }

    @Override
    @Transactional
    public void truncateTable() {
        rolesRepository.deleteAll();
    }

    @Override
    public Role findRoleById(Long id) {
        return rolesRepository.getById(id);
    }

    @Override
    public Set<Role> getRolesById(long[] selectedRoles) {
        return Arrays
                .stream(selectedRoles)
                .mapToObj(this::findRoleById)
                .collect(Collectors.toSet());
    }
}
