package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.services.PetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable Long id) {
        PetDTO petDTO = petService.findById(id);
        return ResponseEntity.ok(petDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PetDTO>> findAll(Pageable pageable) {
        Page<PetDTO> petDTOPage = petService.findAll(pageable);
        return ResponseEntity.ok(petDTOPage);
    }

    @PostMapping
    public ResponseEntity<PetDTO> insert(@RequestBody PetDTO petDTO) {
        petDTO = petService.insert(petDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(petDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(petDTO);
    }
}
