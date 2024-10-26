package com.delivery_to_door.deliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }

    public void sendEmail(String to,String subject, String body)
    {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(body);
        mailSender.send(email);
    }

}
