package com.petcare.petcare.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petcare.petcare.model.entities.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class OwnerDTO {

    @Schema(description = "Database generated pet ID")
    private Long id;
    private String name;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private Set<RoleDTO> roles = new HashSet<>();

    public OwnerDTO() {
    }

    public OwnerDTO(Owner entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birthDate = entity.getBirthDate();

        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));

        /*for (GrantedAuthority role : entity.getRoles()) {
            roles.add(role.getAuthority());
        }*/
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
