package com.example.userservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserRequest {
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
}
