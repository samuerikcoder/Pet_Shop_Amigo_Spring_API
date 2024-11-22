package com.example.demo.controller;

import com.example.demo.dto.PetPostDto;
import com.example.demo.dto.PetPutDto;
import com.example.demo.responses.PetResponseDto;
import com.example.demo.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pets")
@RestController
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetResponseDto createPet(
            @RequestBody PetPostDto dto
    ) {
        return petService.createPet(dto);
    }

    @PutMapping
    public PetResponseDto updatePet(
            @RequestBody PetPutDto dto
    ) {
        return petService.updatePet(dto);
    }

    @GetMapping("/getAllByUserId/{user-id}")
    public List<PetResponseDto> findAllPetsByUserId(
        @PathVariable("user-id") Long userId
    ) {
        return petService.findAllPetsByUserId(userId);
    }

    @GetMapping("/{pet-id}")
    public PetResponseDto findPetById(
            @PathVariable("pet-id") Long id
    ) {
        return petService.findPetById(id);
    }

    @DeleteMapping("/{pet-id}")
    public PetResponseDto deletePet(
            @PathVariable("pet-id") Long id
    ) {
        return petService.deletePet(id);
    }
}
