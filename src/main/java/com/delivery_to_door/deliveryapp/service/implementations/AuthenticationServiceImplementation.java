package com.delivery_to_door.deliveryapp.service.implementations;

import com.delivery_to_door.deliveryapp.dto.ApiResponse;
import com.delivery_to_door.deliveryapp.dto.requests.UserRequestDto;
import com.delivery_to_door.deliveryapp.model.User;
import com.delivery_to_door.deliveryapp.repository.UserRepository;
import com.delivery_to_door.deliveryapp.service.*;
import com.delivery_to_door.deliveryapp.utils.Common;
import com.delivery_to_door.deliveryapp.utils.ResponseEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private Common commonService;

    @Autowired
    private RabbitMQProducer queueProducer;

    @Override
    public ResponseEntity<ApiResponse<Boolean>> register(UserRequestDto body)
    {
        try{

            if(body.getEmail() == null || body.getPassword() == null) return ResponseEntityBuilder.badRequest("Please Provide Valid Email And Password");

            Optional<User> existingUser = userRepository.findByEmail(body.getEmail());

            if(existingUser.isPresent()) return ResponseEntityBuilder.badRequest("Email Address Already Exists");

            User newUser = new User(body.getEmail(),passwordEncoder.encode(body.getPassword()));
            newUser.setOtp(commonService.generateOTP());

            User savedUser = userRepository.save(newUser);

            queueProducer.sendNotification(savedUser.getId());

            return ResponseEntityBuilder.success(true,"Registered Successfully");

        }catch (Exception e){
            return ResponseEntityBuilder.serverError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<User>> login(UserRequestDto body)
    {
        try{

            if(body.getEmail() == null || body.getPassword() == null) return ResponseEntityBuilder.badRequest("Please Provide Valid Email And Password");

            Optional<User> foundUser = userRepository.findByEmail(body.getEmail());

            if(!foundUser.isPresent()) return ResponseEntityBuilder.notFound("Cannot Find User.");

            boolean passwordMatch = passwordEncoder.matches(body.getPassword(),foundUser.get().getPassword());

            if(!passwordMatch) return ResponseEntityBuilder.unAuthorizedRequest("Invalid Credentails");

            User user = foundUser.get();

            String token = jwtService.generateToken(userDetailsService.loadUserById(user.getId()));

            user.setToken(token);

            return ResponseEntityBuilder.success(user,"Logged In Successfully");

        }catch (Exception e){
            return ResponseEntityBuilder.serverError(e.getMessage());
        }
    }
}
