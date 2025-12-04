package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.model.enums.Gender;
import com.petcare.petcare.model.enums.Species;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PetMinDTO {

    private Long id;

    private String name;
    private String breed;
    private Double weight;

    public PetMinDTO() {
    }

    public PetMinDTO(Long id, String name, String breed, Double weight) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
    }

    public PetMinDTO(Pet entity) {
        id = entity.getId();
        name = entity.getName();
        breed = entity.getBreed();
        weight = entity.getWeight();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public Double getWeight() {
        return weight;
    }

}
