package com.example.demo.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private String cellphone;

    public UserResponse(Long id, String username, String email, String cellphone) {
        this.id = id;
        this.name = username;
        this.email = email;
        this.cellphone = cellphone;
    }
}
