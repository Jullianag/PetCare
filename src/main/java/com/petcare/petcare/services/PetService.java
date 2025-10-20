package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional(readOnly = true)
    public PetDTO findById(Long id) {
        Pet pet = petRepository.findById(id).get();
        return new PetDTO(pet);
    }
}
