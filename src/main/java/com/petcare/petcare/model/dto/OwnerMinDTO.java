package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Owner;

public class OwnerMinDTO {

    private Long id;
    private String name;

    public OwnerMinDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerMinDTO(Owner entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
