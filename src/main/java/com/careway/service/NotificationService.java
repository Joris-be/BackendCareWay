package com.careway.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.careway.dao.NotificationRepository;
import com.careway.entity.Notification;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification non trouvée"));
    }

    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Integer id, Notification notificationData) {
        Notification notification = getNotificationById(id);
        notification.setTitle(notificationData.getTitle());
        notification.setMessage(notificationData.getMessage());
        notification.setType(notificationData.getType());
        notification.setDate(notificationData.getDate());
        notification.setRead(notificationData.getRead());
        return notificationRepository.save(notification);
    }

    public void markAsRead(Integer id) {
        Notification notification = getNotificationById(id);
        notification.setRead(true);
        notificationRepository.save(notification);
    }

    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }
}
