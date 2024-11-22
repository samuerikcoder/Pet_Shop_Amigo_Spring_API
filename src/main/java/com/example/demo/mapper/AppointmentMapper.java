package com.example.demo.mapper;

import com.example.demo.dto.AppointmentPostDto;
import com.example.demo.model.Appointment;
import com.example.demo.model.Pet;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.User;
import com.example.demo.responses.AppointmentResponse;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMapper {
    public Appointment toAppointment(AppointmentPostDto dto) {
        var appointment = new Appointment();

        var user = new User();
        user.setId(dto.userId());

        var pet = new Pet();
        pet.setId(dto.petId());

        var service = new ServiceEntity();
        service.setId(dto.serviceId());

        appointment.setUser(user);
        appointment.setPet(pet);
        appointment.setService(service);
        appointment.setStartDateTime(dto.startDateTime());
        appointment.setSendMessage(dto.sendMessage());

        return appointment;
    }

    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getUser().getId(),
                appointment.getPet().getId(),
                appointment.getService().getId(),
                appointment.getStartDateTime(),
                appointment.getEndDateTime(),
                appointment.getStatus(),
                appointment.getSendMessage()
        );
    }
}
