package com.tecapro.demobinhan.controller;

import com.tecapro.demobinhan.model.Chat;
import com.tecapro.demobinhan.model.Notification;
import com.tecapro.demobinhan.model.Orders;
import com.tecapro.demobinhan.service.chat.IChatService;
import com.tecapro.demobinhan.service.notification.INotificationService;
import com.tecapro.demobinhan.service.order.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@RestController
public class WebSocketController {
    @Autowired
    private IChatService chatService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IOrdersService ordersService;

    @MessageMapping("/chats")
    @SendTo("/topic/chats")
    public Chat chatting(Chat chat) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        chat.setTime(date);
        chatService.save(chat);
        return chat;
    }

    @MessageMapping("/notifications")
    @SendTo("/topic/notifications")
    public Notification pushNotification(Notification notification){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        notification.setCreateDate(date);
        notificationService.save(notification);
        return notification;
    }

    @MessageMapping("/orders")
    @SendTo("/topic/orders")
    public Orders addOrders(Orders orders){
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        orders.setCreateDate(date);
        return orders;
    }
}
