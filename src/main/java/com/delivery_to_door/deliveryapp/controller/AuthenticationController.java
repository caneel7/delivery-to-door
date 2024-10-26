package com.delivery_to_door.deliveryapp.controller;

import com.delivery_to_door.deliveryapp.dto.ApiResponse;
import com.delivery_to_door.deliveryapp.dto.requests.UserRequestDto;
import com.delivery_to_door.deliveryapp.model.User;
import com.delivery_to_door.deliveryapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<Boolean>> register(@RequestBody UserRequestDto body)
    {
        return authenticationService.register(body);
    }

    @PostMapping(value = "login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody UserRequestDto body)
    {
        return authenticationService.login(body);
    }

    @PostMapping("verify-otp")
    public ResponseEntity<ApiResponse<Boolean>> verifyOtp(@RequestBody UserRequestDto body)
    {
        return authenticationService.verifyOTP(body);
    }

}
