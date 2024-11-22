package com.example.demo.mapper;

import com.example.demo.dto.PetPostDto;
import com.example.demo.dto.PetPutDto;
import com.example.demo.model.Pet;
import com.example.demo.model.User;
import com.example.demo.responses.PetResponseDto;
import org.springframework.stereotype.Service;

@Service
public class PetMapper {
    public Pet toPet(PetPostDto dto) {
        var pet = new Pet();
        pet.setName(dto.name());
        pet.setAge(dto.age());
        pet.setRace(dto.race());
        pet.setGender(dto.gender());
        pet.setSpecies(dto.species());
        pet.setSize(dto.size());
        pet.setWeight(dto.weight());

        var user = new User();
        user.setId(dto.userId());

        pet.setUser(user);

        return pet;
    }

    public Pet toPet(PetPutDto dto) {
        var pet = new Pet();
        pet.setId(dto.id());
        pet.setName(dto.name());
        pet.setAge(dto.age());
        pet.setRace(dto.race());
        pet.setGender(dto.gender());
        pet.setSpecies(dto.species());
        pet.setSize(dto.size());
        pet.setWeight(dto.weight());

        var user = new User();
        user.setId(dto.userId());

        pet.setUser(user);

        return pet;
    }

    public PetResponseDto toPetResponseDto(Pet pet) {
        return new PetResponseDto(
                pet.getId(),
                pet.getName(),
                pet.getSpecies(),
                pet.getGender(),
                pet.getAge(),
                pet.getRace(),
                pet.getSize(),
                pet.getWeight(),
                pet.getUser().getId()
        );
    }
}
