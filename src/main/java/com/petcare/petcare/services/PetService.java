package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.dto.PetMinDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.repositories.PetRepository;
import com.petcare.petcare.services.exceptions.DatabaseException;
import com.petcare.petcare.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Transactional(readOnly = true)
    public PetDTO findById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Pet not found for this id: " + id + " .")
        );
        return new PetDTO(pet);
    }

    @Transactional(readOnly = true)
    public Page<PetMinDTO> findAll(String name, Pageable pageable) {
        Page<Pet> petList = petRepository.searchPetsByName(name, pageable);
        return petList.map(PetMinDTO::new);
    }

    @Transactional
    public PetDTO insert(PetDTO dto) {

        Pet entity = new Pet();
        copyDtoToEntity(dto, entity);

        return new PetDTO(petRepository.save(entity));
    }

    @Transactional
    public PetDTO update(Long id, PetDTO dto) {

        try {
            Pet entity = petRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new PetDTO(petRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Pet not found for this id: " + id + " .");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found for this id: " + id + " .");
        }
        try {
            petRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity violation");
        }
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
