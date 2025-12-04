package com.petcare.petcare.services;

import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.services.exceptions.ForbiddenException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final OwnerService ownerService;

    public AuthService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public void validateSelfOrAdmin(long ownerId) {

        Owner me = ownerService.authenticated();
        if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(ownerId)) {
            throw new ForbiddenException("Access denied!");
        }
    }
}
