package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.OwnerDTO;
import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.services.OwnerService;
import com.petcare.petcare.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Owners", description = "Endpoints for owners management")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(
            description = "Endpoint for find me",
            summary = "Find me",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<OwnerDTO> findMe() {
        OwnerDTO ownerDTO = ownerService.getMe();
        return ResponseEntity.ok(ownerDTO);
    }

}
