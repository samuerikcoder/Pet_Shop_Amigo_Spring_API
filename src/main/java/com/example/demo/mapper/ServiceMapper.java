package com.example.demo.mapper;

import com.example.demo.dto.PetPostDto;
import com.example.demo.dto.PetPutDto;
import com.example.demo.dto.ServicePostDto;
import com.example.demo.dto.ServicePutDto;
import com.example.demo.model.Pet;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.User;
import com.example.demo.responses.PetResponseDto;
import com.example.demo.responses.ServiceResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ServiceMapper {
    public ServiceEntity toService(ServicePostDto dto){
        var service = new ServiceEntity();
        service.setName(dto.name());
        service.setDuration(dto.duration());
        service.setValue(dto.value());

        return service;
    }

    public ServiceEntity toService(ServicePutDto dto) {
        var service = new ServiceEntity();
        service.setId(dto.id());
        service.setName(dto.name());
        service.setDuration(dto.duration());
        service.setValue(dto.value());

        return service;

}

    public ServiceResponseDto toServiceResponseDto(ServiceEntity service) {
        return new ServiceResponseDto(
                service.getId(),
                service.getName(),
                service.getDuration(),
                service.getValue()
        );
    }
}