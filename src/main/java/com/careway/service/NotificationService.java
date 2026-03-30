package com.careway.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careway.dao.NotificationRepository;
import com.careway.dto.NotificationDTO;
import com.careway.entity.Notification;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotificationById(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return convertToDTO(notification);
    }

    public NotificationDTO saveNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        Notification saved = notificationRepository.save(notification);
        return convertToDTO(saved);
    }

    public NotificationDTO updateNotification(Integer id, NotificationDTO notificationDTO) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setTitle(notificationDTO.getTitle());
        notification.setMessage(notificationDTO.getMessage());
        notification.setType(notificationDTO.getType());
        notification.setRead(notificationDTO.getRead());
        notification.setDate(notificationDTO.getDate());
        Notification updated = notificationRepository.save(notification);
        return convertToDTO(updated);
    }

    public NotificationDTO markAsRead(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        Notification updated = notificationRepository.save(notification);
        return convertToDTO(updated);
    }

    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    private NotificationDTO convertToDTO(Notification notification) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setPatientId(notification.getPatientId());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setType(notification.getType());
        dto.setRead(notification.getRead());
        dto.setDate(notification.getDate());
        return dto;
    }

    private Notification convertToEntity(NotificationDTO dto) {
        Notification notification = new Notification();
        notification.setPatientId(dto.getPatientId());
        notification.setTitle(dto.getTitle());
        notification.setMessage(dto.getMessage());
        notification.setType(dto.getType());
        notification.setRead(dto.getRead() != null ? dto.getRead() : false);
        notification.setDate(dto.getDate());
        return notification;
    }
}
