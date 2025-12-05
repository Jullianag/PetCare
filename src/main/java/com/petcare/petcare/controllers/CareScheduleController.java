package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.CareScheduleDTO;
import com.petcare.petcare.services.CareScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cares")
@Tag(name = "Care Schedules", description = "Endpoints for care schedules management")
public class CareScheduleController {

    private final CareScheduleService careScheduleService;


    public CareScheduleController(CareScheduleService careScheduleService) {
        this.careScheduleService = careScheduleService;
    }

    @Operation(
            description = "Get care schedule by id",
            summary = "Get care schedule by id",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Not Found", responseCode = "404")
            }
    )
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CareScheduleDTO> findById(@PathVariable Long id) {
        CareScheduleDTO careScheduleDTO = careScheduleService.findById(id);
        return ResponseEntity.ok(careScheduleDTO);
    }
}
