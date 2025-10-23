package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.model.enums.Gender;
import com.petcare.petcare.model.enums.Species;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PetDTO {

    private Long id;
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long.")
    @NotBlank(message = "Name cannot be blank.")
    private String name;
    @NotNull (message = "Species cannot be blank.")
    private Species species;
    private String breed;
    private Gender gender;
    @PastOrPresent(message = "Birth date must be in the past or present.")
    private LocalDate birthDate;
    @Positive(message = "Weight must be positive.")
    private Double weight;

    public PetDTO() {
    }

    public PetDTO(Long id, String name, Species species, String breed, Gender gender, LocalDate birthDate, Double weight) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public PetDTO(Pet entity) {
        id = entity.getId();
        name = entity.getName();
        species = entity.getSpecies();
        breed = entity.getBreed();
        gender = entity.getGender();
        birthDate = entity.getBirthDate();
        weight = entity.getWeight();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Species getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Double getWeight() {
        return weight;
    }
}
