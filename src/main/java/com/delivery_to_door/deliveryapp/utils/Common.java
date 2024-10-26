package com.delivery_to_door.deliveryapp.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Common {

    public String generateOTP()
    {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

}
