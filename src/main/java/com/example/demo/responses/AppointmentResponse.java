package com.example.demo.responses;

import com.example.demo.enums.Status;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record AppointmentResponse(
        Long id,
        Long userId,
        Long petId,
        Long serviceId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        Status status,
        boolean sendMessage
) {
}
