package com.petcare.petcare.model.dto;

import com.petcare.petcare.model.entities.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public class RoleDTO {

    @Schema(description = "Role ID", example = "1")
    private Long id;

    @Schema(
            description = "Role authority",
            example = "ROLE_ADMIN"
    )
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
