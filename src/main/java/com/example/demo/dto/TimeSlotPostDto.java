package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record TimeSlotPostDto(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        boolean available
) {
}
