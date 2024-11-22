package com.example.demo.repository;

import com.example.demo.model.TimeSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
    boolean existsByDateAndStartTime(LocalDate date, LocalTime startTime);

    List<TimeSlot> findAllByDate(LocalDate data);

    @Query("SELECT ts FROM TimeSlot ts " +
            "WHERE ts.date = :date " +
            "AND ts.startTime >= :startTime " +
            "AND ts.endTime <= :endTime " +
            "AND ts.available = true")
    List<TimeSlot> findAvailableSlots(
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);
}
