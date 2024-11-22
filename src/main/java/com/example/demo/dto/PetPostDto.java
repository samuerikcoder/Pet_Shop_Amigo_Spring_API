package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Species;

public record PetPostDto(
        String name,
        Species species,
        Gender gender,
        Integer age,
        String race,
        Double size,
        Double weight,
        Long userId
) {
}
