package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.services.PetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "/{id}")
    public PetDTO findById(@PathVariable Long id) {
        return petService.findById(id);
    }
}
