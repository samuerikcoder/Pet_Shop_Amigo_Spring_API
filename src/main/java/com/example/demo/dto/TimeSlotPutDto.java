package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotPutDto(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        boolean available
) {
}
