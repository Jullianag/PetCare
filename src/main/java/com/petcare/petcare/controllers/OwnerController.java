package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.OwnerDTO;
import com.petcare.petcare.model.dto.OwnerInsertDTO;
import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.dto.PetMinDTO;
import com.petcare.petcare.services.OwnerService;
import com.petcare.petcare.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me", produces = "application/json")
    public ResponseEntity<OwnerDTO> findMe() {
        OwnerDTO ownerDTO = ownerService.getMe();
        return ResponseEntity.ok(ownerDTO);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<OwnerDTO>> findAll(
            Pageable pageable) {
        Page<OwnerDTO> ownerDTOPage = ownerService.findAll(pageable);
        return ResponseEntity.ok(ownerDTOPage);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
        OwnerDTO ownerDTO = ownerService.findById(id);
        return ResponseEntity.ok(ownerDTO);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<OwnerDTO> insert(@Valid @RequestBody OwnerInsertDTO ownerInsertDTO) {
        OwnerDTO ownerDTO = ownerService.insert(ownerInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ownerDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(ownerDTO);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OwnerDTO> update(@PathVariable Long id, @Valid @RequestBody OwnerDTO ownerDTO) {
        ownerDTO = ownerService.update(id, ownerDTO);
        return ResponseEntity.ok(ownerDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
