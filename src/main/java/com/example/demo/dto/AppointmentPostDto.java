package com.example.demo.dto;


import java.time.LocalDateTime;

public record AppointmentPostDto(
    Long userId,
    Long petId,
    Long serviceId,
    LocalDateTime startDateTime,
    boolean sendMessage
) {
}
