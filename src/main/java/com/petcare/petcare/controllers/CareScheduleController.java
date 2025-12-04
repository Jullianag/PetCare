package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.CareScheduleDTO;
import com.petcare.petcare.services.CareScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cares")
public class CareScheduleController {

    private final CareScheduleService careScheduleService;


    public CareScheduleController(CareScheduleService careScheduleService) {
        this.careScheduleService = careScheduleService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CareScheduleDTO> findById(@PathVariable Long id) {
        CareScheduleDTO careScheduleDTO = careScheduleService.findById(id);
        return ResponseEntity.ok(careScheduleDTO);
    }
}
