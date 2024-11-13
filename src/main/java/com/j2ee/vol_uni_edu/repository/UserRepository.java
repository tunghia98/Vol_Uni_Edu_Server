package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.Charity;
import com.j2ee.vol_uni_edu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
}