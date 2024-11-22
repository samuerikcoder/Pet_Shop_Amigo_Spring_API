package com.example.demo.service;

import com.example.demo.dto.ServicePostDto;
import com.example.demo.dto.ServicePutDto;
import com.example.demo.mapper.ServiceMapper;
import com.example.demo.repository.ServiceRepository;
import com.example.demo.responses.ServiceResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper mapper;

    public ServiceService(ServiceRepository serviceRepository, ServiceMapper mapper) {
        this.serviceRepository = serviceRepository;
        this.mapper = mapper;
    }

    public ServiceResponseDto createService(ServicePostDto servicePostDto)  {
        var service = mapper.toService(servicePostDto);
        var savedService = serviceRepository.save(service);

        return mapper.toServiceResponseDto(savedService);
    }

    public ServiceResponseDto findServiceById(Long id) {
        var service = serviceRepository.findById(id).orElse(null);

        return service != null ? mapper.toServiceResponseDto(service) : null;

    }

    public List<ServiceResponseDto> findAllServices() {
        return StreamSupport.stream(serviceRepository.findAll().spliterator(), false)
                .map(mapper::toServiceResponseDto)
                .collect(Collectors.toList());
    }


    public ServiceResponseDto updateService(ServicePutDto dto) {
        var service = mapper.toService(dto);
        var updateService = serviceRepository.save(service);

        return mapper.toServiceResponseDto(updateService);
    }

    public ServiceResponseDto deleteService(long id) {
        var deletedService = serviceRepository.findById(id).orElse(null);

        if(deletedService == null) throw new RuntimeException("Service not found");

        serviceRepository.deleteById(id);

        return mapper.toServiceResponseDto(deletedService);
    }


}