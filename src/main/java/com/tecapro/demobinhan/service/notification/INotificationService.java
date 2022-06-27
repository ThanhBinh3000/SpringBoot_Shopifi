package com.tecapro.demobinhan.service.notification;


import com.tecapro.demobinhan.model.Notification;
import com.tecapro.demobinhan.model.auth.User;
import com.tecapro.demobinhan.service.IGeneralService;

public interface INotificationService extends IGeneralService<Notification> {
    Iterable<Notification> findAllByStatusIsFalseAndUser(User user);

    Iterable<Notification> findAllDateDesc(Long id);
}
