package com.delivery_to_door.deliveryapp.service;

import com.delivery_to_door.deliveryapp.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {

    @RabbitListener(queues = "notification.queue")
    public void notificationListener(String userId)
    {
        System.out.println("Listening to Notification Queue " + userId);


    }

}
