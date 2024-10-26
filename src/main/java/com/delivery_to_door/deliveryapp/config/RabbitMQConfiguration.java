package com.delivery_to_door.deliveryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue notificationQueue()
    {
        return new Queue("notification.queue",true);
    }

}
