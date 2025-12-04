package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Owner;
import io.swagger.v3.oas.annotations.media.Schema;

public class OwnerMinDTO {

    @Schema(description = "Database generated pet ID")
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
