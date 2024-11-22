package com.example.demo.service;

import com.example.demo.dto.AppointmentPostDto;
import com.example.demo.enums.Status;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.TimeSlot;
import com.example.demo.repository.*;
import com.example.demo.responses.AppointmentResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    private TimeSlotRepository timeSlotRepository;

    private ServiceRepository serviceRepository;

    private AppointmentMapper appointmentMapper;

    private UserRepository userRepository;

    private PetRepository petRepository;

    private EmailService emailService;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            TimeSlotRepository timeSlotRepository,
            ServiceRepository serviceRepository,
            AppointmentMapper appointmentMapper,
            UserRepository userRepository,
            PetRepository petRepository,
            EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.serviceRepository = serviceRepository;
        this.appointmentMapper = appointmentMapper;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
        this.emailService = emailService;
    }

    public AppointmentResponse createAppointment(AppointmentPostDto dto) {
        ServiceEntity service =  serviceRepository.findById(dto.serviceId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));

        LocalDateTime startDateTime = dto.startDateTime();
        LocalDateTime endDateTime = startDateTime.plusMinutes(service.getDuration());

        List<TimeSlot> availableSlots = timeSlotRepository.findAvailableSlots(
                startDateTime.toLocalDate(),
                startDateTime.toLocalTime(),
                endDateTime.toLocalTime()
        );

        int expectedSlots = (int) Math.ceil(service.getDuration() / 30.0);
        if (availableSlots.size() < expectedSlots) {
            throw new IllegalStateException("Time slots indisponíveis");
        }

        availableSlots.forEach(slot -> {
            slot.setAvailable(false);
            timeSlotRepository.save(slot);
        });

        var appointment = appointmentMapper.toAppointment(dto);
        appointment.setEndDateTime(endDateTime);
        appointment.setStatus(Status.Confirmed);

        var savedAppointment = appointmentRepository.save(appointment);

        return appointmentMapper.toAppointmentResponse(savedAppointment);
    }

    public AppointmentResponse findAppointmentById(Long id) {
        var appointment = appointmentRepository.findById(id).orElse(null);

        return appointment != null ?
                appointmentMapper.toAppointmentResponse(appointment) :
                null;
    }

    public void updateStatus(Long id, Status status) {
        var appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        var oldStatus = appointment.getStatus();
        appointment.setStatus(status);

        if(oldStatus == Status.Canceled && status == Status.Finished) {
            throw new IllegalStateException("Não é possível finalizar um agendamento com status cancelado.");
        }

        if(oldStatus == Status.Finished && status == Status.Canceled) {
            throw new IllegalStateException("Não é possível cancelar um agendamento finalizado");
        }

        if(status == Status.Canceled) {
            List<TimeSlot> availableSlots = timeSlotRepository.findAvailableSlots(
                    appointment.getStartDateTime().toLocalDate(),
                    appointment.getStartDateTime().toLocalTime(),
                    appointment.getEndDateTime().toLocalTime()
            );

            availableSlots.forEach(slot -> {
                slot.setAvailable(true);
                timeSlotRepository.save(slot);
            });
        }

        var service = serviceRepository.findById(appointment.getService().getId())
                .orElseThrow(() -> new IllegalArgumentException("Serviço não encontrado"));
        var pet = petRepository.findById(appointment.getPet().getId())
                .orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));;
        var user = userRepository.findById(appointment.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));;

        if (status == Status.Finished) {
            String subject = service.getName() + " finalizado!";
            String htmlMessage = "<html>"
                    + "<body style=\"font-family: Arial, sans-serif;\">"
                    + "<p style=\"font-size: 16px;\">Olá " + user.getUsername() + ", seu pet " + pet.getName() + " está pronto para ser buscado! </p>"
                    + "</body>"
                    + "</html>";

            try {
                emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
            } catch (MessagingException e) {
                // Handle email sending exception
                e.printStackTrace();
            }
        }

        appointmentRepository.save(appointment);
    }

}
