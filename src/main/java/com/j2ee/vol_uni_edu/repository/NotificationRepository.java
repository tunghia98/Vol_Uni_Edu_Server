package com.j2ee.vol_uni_edu.repository;

import com.j2ee.vol_uni_edu.models.Notification;
import com.j2ee.vol_uni_edu.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndIsRead(User user, boolean isRead);
    List<Notification> findByUser(User user);
}
