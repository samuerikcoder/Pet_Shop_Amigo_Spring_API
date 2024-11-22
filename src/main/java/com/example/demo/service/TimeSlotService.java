package com.example.demo.service;

import com.example.demo.dto.TimeSlotPostDto;
import com.example.demo.dto.TimeSlotPutDto;
import com.example.demo.mapper.TimeSlotMapper;
import com.example.demo.model.TimeSlot;
import com.example.demo.repository.TimeSlotRepository;
import com.example.demo.responses.TimeSlotResponseDto;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    private final TimeSlotMapper mapper;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, TimeSlotMapper mapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.mapper = mapper;
    }

    public TimeSlotResponseDto createTimeSlot(TimeSlotPostDto dto) {
        var timeSlot = mapper.toTimeSlot(dto);
        var savedTimeSlot = timeSlotRepository.save(timeSlot);

        return mapper.toTimeSlotResponseDto(savedTimeSlot);
    }

    public TimeSlotResponseDto findTimeSlotById(Long id) {
        var timeSlot = timeSlotRepository.findById(id).orElse(null);

        return timeSlot != null ? mapper.toTimeSlotResponseDto(timeSlot) : null;
    }

    public List<TimeSlotResponseDto> findAllByDate(LocalDate date) {
        return timeSlotRepository.findAllByDate(date)
                .stream()
                .map(mapper::toTimeSlotResponseDto)
                .collect(Collectors.toList());
    }

    public TimeSlotResponseDto updateTimeSlot(TimeSlotPutDto dto) {
        var timeSlot = mapper.toTimeSlot(dto);
        var updatedTimeSlot = timeSlotRepository.save(timeSlot);

        return mapper.toTimeSlotResponseDto(updatedTimeSlot);
    }

    public TimeSlotResponseDto deleteTimeSlot(long id) {
        var deletedTimeSlot = timeSlotRepository.findById(id).orElse(null);

        if (deletedTimeSlot == null) throw new RuntimeException("Time Slot not found");

        timeSlotRepository.deleteById(id);

        return mapper.toTimeSlotResponseDto(deletedTimeSlot);
    }

    public void generateTimeSlots(LocalDate startDate, LocalDate endDate) {
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime lunchStart = LocalTime.of(12, 0);
        LocalTime lunchEnd = LocalTime.of(13, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        Duration slotDuration = Duration.ofMinutes(30);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                LocalTime currentTime = startTime;
                while (currentTime.isBefore(lunchStart)) {
                    var timeSlotDto = new TimeSlotPostDto (
                            date,
                            currentTime,
                            currentTime.plus(slotDuration),
                            true
                    );
                    createTimeSlot(timeSlotDto);
                    currentTime = currentTime.plus(slotDuration);
                }

                currentTime = lunchEnd;
                while (currentTime.isBefore(endTime)) {
                    var timeSlotDto = new TimeSlotPostDto (
                            date,
                            currentTime,
                            currentTime.plus(slotDuration),
                            true
                    );
                    createTimeSlot(timeSlotDto);
                    currentTime = currentTime.plus(slotDuration);
                }
            }
        }
    }
}
