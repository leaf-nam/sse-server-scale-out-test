package com.ssafy.mugit.notification.repository.querydsl;

import com.ssafy.mugit.global.dto.NotificationDto;
import com.ssafy.mugit.notification.entity.Notification;

import java.util.List;

public interface NotificationCustomRepository {
    List<Notification> findAllReadableByUserId(Long userId);

    List<NotificationDto> findAllReadableDtoByUserIdOrderByCreatedAt(Long userId);

    Notification findByIdWithUserId(Long notificationId, Long userId);
}
