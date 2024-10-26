package com.delivery_to_door.deliveryapp.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Optional;

@Data
public class UserRequestDto {

    private String id;
    private String email;
    private String mobile;
    private String password;
    private Optional<String> token;

}
