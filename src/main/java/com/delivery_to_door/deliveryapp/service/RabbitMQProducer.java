package com.delivery_to_door.deliveryapp.service;

import com.delivery_to_door.deliveryapp.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQProducer(RabbitTemplate template)
    {
        this.rabbitTemplate = template;
    }

    public void sendNotification(String userId)
    {
        rabbitTemplate.convertAndSend("notification.queue",userId);
    }
}
