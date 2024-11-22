package com.example.demo.responses;

import com.example.demo.enums.Gender;
import com.example.demo.enums.Species;

public record PetResponseDto(
        Long id,
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
