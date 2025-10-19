package com.petcare.petcare.model.entities;

import com.petcare.petcare.model.enums.Gender;
import com.petcare.petcare.model.enums.Species;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Species species;
    private String breed;
    private Gender gender;
    private LocalDate birthDate;
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToOne(mappedBy = "pet")
    private CareSchedule careSchedule;
}
