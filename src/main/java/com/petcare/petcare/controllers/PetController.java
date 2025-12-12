package com.petcare.petcare.controllers;

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
@RequestMapping(value = "/pets")
@Tag(name = "Pets", description = "Endpoints for pets management")
public class PetController {

    private final PetService petService;

    private final OwnerService ownerService;

    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @Operation(
            description = "Get pet by id",
            summary = "Get pet by id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
        PetDTO petDTO = petService.findById(id);
        return ResponseEntity.ok(petDTO);
    }

    @Operation(
            description = "Get all pets",
            summary = "List all pets",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<PetMinDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable) {
        Page<PetMinDTO> petDTOPage = petService.findAll(name, pageable);
        return ResponseEntity.ok(petDTOPage);
    }

    @Operation(
            description = "Create a new pet",
            summary = "Create a new pet",
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping(produces = "application/json")
    public ResponseEntity<PetDTO> insert(@Valid @RequestBody PetDTO petDTO) {
        petDTO = petService.insert(petDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(petDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(petDTO);
    }

    @Operation(
            description = "Update a pet",
            summary = "Update a pet",
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
    public ResponseEntity<PetDTO> update(@PathVariable Long id, @Valid @RequestBody PetDTO petDTO) {
        petDTO = petService.update(id, petDTO);
        return ResponseEntity.ok(petDTO);
    }

    @Operation(
            description = "Delete a pet",
            summary = "Delete a pet",
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
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
