package com.example.demo.controller;

import com.example.demo.dto.ServicePostDto;
import com.example.demo.dto.ServicePutDto;
import com.example.demo.dto.TimeSlotPostDto;
import com.example.demo.dto.TimeSlotPutDto;
import com.example.demo.responses.ServiceResponseDto;
import com.example.demo.responses.TimeSlotResponseDto;
import com.example.demo.service.TimeSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/timeSlots")
@RestController
public class TimeSlotController {
    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateTimeSlots(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        timeSlotService.generateTimeSlots(start, end);
        return ResponseEntity.ok("Time slots generated successfully!");
    }

    @PostMapping
    public TimeSlotResponseDto createTimeSlot(
            @RequestBody TimeSlotPostDto dto
    ) {
        return timeSlotService.createTimeSlot(dto);
    }

    @PutMapping
    public TimeSlotResponseDto updateTimeSlot(
            @RequestBody TimeSlotPutDto dto
    ) {
        return timeSlotService.updateTimeSlot(dto);
    }

    @GetMapping("/{date}")
    public List<TimeSlotResponseDto> findAllTimeSlotsByDate(
            @PathVariable("date") LocalDate date
    ) {
        return timeSlotService.findAllByDate(date);
    }

    @GetMapping("/{time-slot-id}")
    public TimeSlotResponseDto findTimeSlotById(
            @PathVariable("time-slot-id") Long id
    ) {
        return timeSlotService.findTimeSlotById(id);
    }

    @DeleteMapping("/{time-slot-id}")
    public TimeSlotResponseDto deleteService(
            @PathVariable("time-slot-id") Long id
    ) {
        return timeSlotService.deleteTimeSlot(id);
    }
}
