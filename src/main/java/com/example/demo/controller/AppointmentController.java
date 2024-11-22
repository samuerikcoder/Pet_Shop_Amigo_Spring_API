package com.example.demo.controller;


import com.example.demo.dto.AppointmentPostDto;
import com.example.demo.enums.Status;
import com.example.demo.responses.AppointmentResponse;
import com.example.demo.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/appointments")
@RestController
public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/{id}")
    public AppointmentResponse findAppointment(
        @PathVariable("id") Long id
    ) {
        return appointmentService.findAppointmentById(id);
    }

    @PostMapping
    public AppointmentResponse createAppointment(
            @RequestBody AppointmentPostDto dto) {
        return appointmentService.createAppointment(dto);
    }

    @PostMapping("/{id}/finish")
    public ResponseEntity<String> finishAppointment(@PathVariable Long id) {
        appointmentService.updateStatus(id, Status.Finished);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long id) {
        appointmentService.updateStatus(id, Status.Canceled);
        return ResponseEntity.noContent().build();
    }
}
