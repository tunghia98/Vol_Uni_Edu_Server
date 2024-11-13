package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.UserRole;
import com.j2ee.vol_uni_edu.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    // Create or Update UserRole
    public UserRole saveUserRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    // Get All UserRoles
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    // Delete UserRole by ID
    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }
}
