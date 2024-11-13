package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
