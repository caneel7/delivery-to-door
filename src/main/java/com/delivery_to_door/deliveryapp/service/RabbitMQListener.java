package com.delivery_to_door.deliveryapp.service;

import com.delivery_to_door.deliveryapp.model.User;
import com.delivery_to_door.deliveryapp.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQListener {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "notification.queue")
    public void notificationListener(String userId)
    {
        System.out.println("Listening to Notification Queue " + userId);

        Optional<User> foundUser = userRepository.findById(userId);
        if(!foundUser.isPresent()) return ;

        User user = foundUser.get();

        String body = String.format("<div>\n" +
                "    <h4>Hello %s,</h4>\n" +
                "    <p>Thank you for joining our service, please use %s to complete the onboarding process</p>\n" +
                "</div>", user.getEmail(),user.getOtp()
        );
//        emailService.sendEmail(user.getEmail(),"Delivery To Door - OTP For verification",body);

    }

}
