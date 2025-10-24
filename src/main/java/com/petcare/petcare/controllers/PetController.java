package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.PetDTO;
import com.petcare.petcare.model.entities.Pet;
import com.petcare.petcare.services.PetService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Page<PetDTO>> findAll(
            @RequestParam(name = "name", defaultValue = "") String name,
            Pageable pageable) {
        Page<PetDTO> petDTOPage = petService.findAll(name, pageable);
        return ResponseEntity.ok(petDTOPage);
    }

    @PostMapping
    public ResponseEntity<PetDTO> insert(@Valid @RequestBody PetDTO petDTO) {
        petDTO = petService.insert(petDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(petDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(petDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PetDTO> update(@PathVariable Long id, @Valid @RequestBody PetDTO petDTO) {
        petDTO = petService.update(id, petDTO);
        return ResponseEntity.ok(petDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
