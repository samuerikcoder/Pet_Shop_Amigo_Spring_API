package com.example.demo.mapper;

import com.example.demo.dto.TimeSlotPostDto;
import com.example.demo.dto.TimeSlotPutDto;
import com.example.demo.model.TimeSlot;
import com.example.demo.responses.TimeSlotResponseDto;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotMapper {
    public TimeSlot toTimeSlot(TimeSlotPostDto dto) {
        var timeSlot = new TimeSlot();
        timeSlot.setDate(dto.date());
        timeSlot.setStartTime(dto.startTime());
        timeSlot.setEndTime(dto.endTime());
        timeSlot.setAvailable(dto.available());

        return timeSlot;
    }

    public TimeSlot toTimeSlot(TimeSlotPutDto dto) {
        var timeSlot = new TimeSlot();
        timeSlot.setId(dto.id());
        timeSlot.setDate(dto.date());
        timeSlot.setStartTime(dto.startTime());
        timeSlot.setEndTime(dto.endTime());
        timeSlot.setAvailable(dto.available());

        return timeSlot;
    }

    public TimeSlotResponseDto toTimeSlotResponseDto(TimeSlot timeSlot) {
        return new TimeSlotResponseDto(
            timeSlot.getId(),
            timeSlot.getDate(),
            timeSlot.getStartTime(),
            timeSlot.getEndTime(),
            timeSlot.getAvailable()
        );
    }
}
