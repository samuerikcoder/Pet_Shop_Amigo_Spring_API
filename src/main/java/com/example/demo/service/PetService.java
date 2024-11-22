package com.example.demo.service;

import com.example.demo.dto.PetPostDto;
import com.example.demo.dto.PetPutDto;
import com.example.demo.mapper.PetMapper;
import com.example.demo.repository.PetRepository;
import com.example.demo.responses.PetResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    private final PetRepository petRepository;

    private final PetMapper mapper;

    public PetService(PetRepository petRepository, PetMapper mapper) {
        this.petRepository = petRepository;
        this.mapper = mapper;
    }

    public PetResponseDto createPet(PetPostDto petPostDto) {
        var pet = mapper.toPet(petPostDto);
        var savedPet = petRepository.save(pet);

        return mapper.toPetResponseDto(savedPet);
    }

    public PetResponseDto findPetById(Long id) {
        var pet = petRepository.findById(id).orElse(null);

        return pet != null ? mapper.toPetResponseDto(pet) : null;
    }

    public List<PetResponseDto> findAllPetsByUserId(Long userId) {
        return petRepository.findAllByUserId(userId)
                .stream()
                .map(mapper::toPetResponseDto)
                .collect(Collectors.toList());
    }

    public PetResponseDto updatePet(PetPutDto dto) {
        var pet = mapper.toPet(dto);
        var updatedPet = petRepository.save(pet);

        return mapper.toPetResponseDto(updatedPet);
    }

    public PetResponseDto deletePet(Long id) {
        var deletedPet = petRepository.findById(id).orElse(null);

        if(deletedPet == null) throw new RuntimeException("Pet not found");

        petRepository.deleteById(id);

        return mapper.toPetResponseDto(deletedPet);
    }


}
