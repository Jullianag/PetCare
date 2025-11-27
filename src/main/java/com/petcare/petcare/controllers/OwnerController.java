package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.OwnerDTO;
import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.services.OwnerService;
import com.petcare.petcare.services.PetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<OwnerDTO> findMe() {
        OwnerDTO ownerDTO = ownerService.getMe();
        return ResponseEntity.ok(ownerDTO);
    }

}
