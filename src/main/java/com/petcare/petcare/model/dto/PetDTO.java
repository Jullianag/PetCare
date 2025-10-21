package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.model.enums.Gender;
import com.petcare.petcare.model.enums.Species;

import java.time.LocalDate;

public class PetDTO {

    private Long id;
    private String name;
    private Species species;
    private String breed;
    private Gender gender;
    private LocalDate birthDate;
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
