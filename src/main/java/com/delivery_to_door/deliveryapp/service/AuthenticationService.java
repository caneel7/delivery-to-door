package com.delivery_to_door.deliveryapp.service;

import com.delivery_to_door.deliveryapp.dto.ApiResponse;
import com.delivery_to_door.deliveryapp.dto.requests.UserRequestDto;
import com.delivery_to_door.deliveryapp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface AuthenticationService {

    ResponseEntity<ApiResponse<Boolean>> register(UserRequestDto body);

    ResponseEntity<ApiResponse<User>> login(UserRequestDto body);
}
