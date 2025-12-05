package com.petcare.petcare.model.dto;

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
