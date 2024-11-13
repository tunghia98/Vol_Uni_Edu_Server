package com.j2ee.vol_uni_edu.controller;

import com.j2ee.vol_uni_edu.models.Notification;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.services.NotificationService;
import com.j2ee.vol_uni_edu.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    // Create a new notification for a user
    @PostMapping("/{userId}")
    public ResponseEntity<Notification> createNotification(
            @PathVariable Long userId, @RequestBody Notification notification) {

        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            notification.setUser(userOpt.get());
            Notification savedNotification = notificationService.saveNotification(notification);
            return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all notifications for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId) {
        Optional<User> userOpt = userService.getUserById(userId);
        if (userOpt.isPresent()) {
            List<Notification> notifications = notificationService.getNotificationsByUser(userOpt.get());
            return new ResponseEntity<>(notifications, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Mark a notification as read
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long notificationId) {
        try {
            Notification updatedNotification = notificationService.markAsRead(notificationId);
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a notification by ID
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        Optional<Notification> notificationOpt = notificationService.getNotificationsById(notificationId);
        if (notificationOpt.isPresent()) {
            notificationService.deleteNotification(notificationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
