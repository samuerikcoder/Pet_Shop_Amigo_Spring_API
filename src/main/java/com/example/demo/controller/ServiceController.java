package com.example.demo.controller;

import com.example.demo.dto.PetPostDto;
import com.example.demo.dto.PetPutDto;
import com.example.demo.dto.ServicePostDto;
import com.example.demo.dto.ServicePutDto;
import com.example.demo.responses.PetResponseDto;
import com.example.demo.responses.ServiceResponseDto;
import com.example.demo.service.PetService;
import com.example.demo.service.ServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/services")
@RestController
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ServiceResponseDto createService(
            @RequestBody ServicePostDto dto
    ) {
        return serviceService.createService(dto);
    }

    @PutMapping
    public ServiceResponseDto updateService(
            @RequestBody ServicePutDto dto
    ) {
        return serviceService.updateService(dto);
    }

    @GetMapping
    public List<ServiceResponseDto> findAllServices() {
        return serviceService.findAllServices();
    }

    @GetMapping("/{service-id}")
    public ServiceResponseDto findServiceById(
            @PathVariable("service-id") Long id
    ) {
        return serviceService.findServiceById(id);
    }

    @DeleteMapping("/{service-id}")
    public ServiceResponseDto deleteService(
            @PathVariable("service-id") Long id
    ) {
        return serviceService.deleteService(id);
    }
}
