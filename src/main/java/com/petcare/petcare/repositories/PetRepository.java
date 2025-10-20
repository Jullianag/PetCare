package com.petcare.petcare.repositories;

import com.petcare.petcare.model.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
