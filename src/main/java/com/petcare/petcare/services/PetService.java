package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public PetDTO insert(PetDTO dto) {

        Pet entity = new Pet();
        copyDtoToEntity(dto, entity);

        return new PetDTO(petRepository.save(entity));
    }

    @Transactional
    public PetDTO update(Long id, PetDTO dto) {

        Pet entity = petRepository.getReferenceById(id);
        copyDtoToEntity(dto, entity);

        return new PetDTO(petRepository.save(entity));
    }

    private void copyDtoToEntity(PetDTO dto, Pet entity) {
        entity.setName(dto.getName());
        entity.setSpecies(dto.getSpecies());
        entity.setBreed(dto.getBreed());
        entity.setGender(dto.getGender());
        entity.setBirthDate(dto.getBirthDate());
        entity.setWeight(dto.getWeight());
    }
}
