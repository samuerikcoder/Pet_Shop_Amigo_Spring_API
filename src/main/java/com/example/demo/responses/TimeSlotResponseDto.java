package com.example.demo.responses;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotResponseDto(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        boolean available
) {
}
