package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create or Update User
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get User by Username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    // Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete User by Username
    public void deleteUserByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Disable User by Username
    public void disableUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setEnabled(false);  // Vô hiệu hóa người dùng
            userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    // Check if User exists by Username
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
}