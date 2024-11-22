package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Species;

public record ServicePutDto(
        Long id,
        String name,
        int duration,
        double value
) {

}