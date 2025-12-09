package com.petcare.petcare.model.dto;

import com.petcare.petcare.services.validation.OwnerInsertValid;

@OwnerInsertValid
public class OwnerInsertDTO extends OwnerDTO {

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
