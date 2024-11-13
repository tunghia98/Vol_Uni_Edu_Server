package com.j2ee.vol_uni_edu.services;

import com.j2ee.vol_uni_edu.models.Notification;
import com.j2ee.vol_uni_edu.models.User;
import com.j2ee.vol_uni_edu.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Create new notification
    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }
    // Lấy thông báo theo ID
    public Optional<Notification> getNotificationsById(Long id) {
        return notificationRepository.findById(id);
    }
    // Get all notifications for a specific user
    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByUser(user);
    }

    // Get unread notifications for a specific user
    public List<Notification> getUnreadNotificationsByUser(User user) {
        return notificationRepository.findByUserAndIsRead(user, false);
    }

    // Mark notification as read
    public Notification markAsRead(Long id) {
        Optional<Notification> notificationOpt = notificationRepository.findById(id);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setRead(true);
            notification.setStatus("read");
            return notificationRepository.save(notification);
        } else {
            throw new RuntimeException("Notification not found");
        }
    }

    // Delete notification by ID
    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
