package com.petcare.petcare.model.dto;

import com.petcare.petcare.services.validation.OwnerInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@OwnerInsertValid
public class OwnerInsertDTO extends OwnerDTO {

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must have at least 8 characters")
    private String password;

    public OwnerInsertDTO() {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
