package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String jwt;

    private UserResponse user;

    public LoginResponse(String jwt, UserResponse user) {
        this.jwt = jwt;
        this.user = user;
    }
}
