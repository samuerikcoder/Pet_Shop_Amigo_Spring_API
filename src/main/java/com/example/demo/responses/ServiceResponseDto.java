package com.example.demo.responses;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Species;

public record ServiceResponseDto(
        Long id,
        String name,
        int duration,
        double value
) {

}