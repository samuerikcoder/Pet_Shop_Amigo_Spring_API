package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyUserDto {
    private String email;

    private String verificationCode;
}
