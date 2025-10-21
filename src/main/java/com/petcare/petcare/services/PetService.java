package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public Page<PetDTO> findAll(Pageable pageable) {
        Page<Pet> petList = petRepository.findAll(pageable);
        return petList.map(PetDTO::new);
    }
}
