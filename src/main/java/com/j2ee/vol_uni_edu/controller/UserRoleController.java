package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.UserRole;
import com.j2ee.vol_uni_edu.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    // Create or Update UserRole
    @PostMapping
    public ResponseEntity<UserRole> createOrUpdateUserRole(@RequestBody UserRole userRole) {
        UserRole savedUserRole = userRoleService.saveUserRole(userRole);
        return ResponseEntity.ok(savedUserRole);
    }

    // Get All UserRoles
    @GetMapping
    public ResponseEntity<List<UserRole>> getAllUserRoles() {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        return ResponseEntity.ok(userRoles);
    }

    // Delete UserRole by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteUserRole(id);
        return ResponseEntity.noContent().build();
    }
}
