package ru.blinov.mygeekspringboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blinov.mygeekspringboot.entities.Role;
import ru.blinov.mygeekspringboot.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleService() {
    }

    public List<Role> getAllRoles() {
        return (List)roleRepository.findAll();
    }

}
