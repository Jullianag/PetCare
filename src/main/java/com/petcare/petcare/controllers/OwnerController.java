package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.*;
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

    @Operation(
            description = "Get all owners",
            summary = "List all owners",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<OwnerDTO>> findAll(
            Pageable pageable) {
        Page<OwnerDTO> ownerDTOPage = ownerService.findAll(pageable);
        return ResponseEntity.ok(ownerDTOPage);
    }

    @Operation(
            description = "Get owner by id",
            summary = "Get owner by id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OwnerDTO> findById(@PathVariable Long id) {
        OwnerDTO ownerDTO = ownerService.findById(id);
        return ResponseEntity.ok(ownerDTO);
    }

    @Operation(
            description = "Create a new owner",
            summary = "Create a new owner",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(produces = "application/json")
    public ResponseEntity<OwnerDTO> insert(@Valid @RequestBody OwnerInsertDTO ownerInsertDTO) {
        OwnerDTO ownerDTO = ownerService.insert(ownerInsertDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ownerDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(ownerDTO);
    }

    @Operation(
            description = "Update a owner",
            summary = "Update a owner",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OwnerDTO> update(@PathVariable Long id, @Valid @RequestBody OwnerUpdateDTO ownerDTO) {
        OwnerDTO newDTO = ownerService.update(id, ownerDTO);
        return ResponseEntity.ok(newDTO);
    }

    @Operation(
            description = "Delete a owner",
            summary = "Delete a owner",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Not Found", responseCode = "404"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ownerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
