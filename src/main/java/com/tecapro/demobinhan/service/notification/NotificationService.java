package com.tecapro.demobinhan.service.notification;

import com.tecapro.demobinhan.model.Notification;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public Iterable<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public void remove(Long id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public Iterable<Notification> findAllByStatusIsFalseAndUser(User user) {
        return notificationRepository.findAllByStatusIsFalseAndUser(user);
    }

    @Override
    public Iterable<Notification> findAllDateDesc(Long id) {
        return notificationRepository.findAllDateDesc(id);
    }
}
