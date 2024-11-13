package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.Role;
import com.j2ee.vol_uni_edu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Create or Update Role
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Get Role by ID
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    // Get Role by Name
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    // Get All Roles
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Delete Role by ID
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
