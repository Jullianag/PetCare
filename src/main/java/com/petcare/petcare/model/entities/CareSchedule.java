package com.petcare.petcare.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_care_schedule")
@Getter
@Setter
public class CareSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate lastVacine;
    private LocalDate nextVacine;
    private String currentMedication;
    private String medicationNotes;
    private LocalDate lastAppointment;
    private LocalDate nextAppointment;
    private String appointmentNotes;
    private LocalDate lastGrooming;
    private String grooming;
    private String notes;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant moment;

    @OneToOne
    private Pet pet;

    public CareSchedule() {
    }

    public CareSchedule(Long id, LocalDate lastVacine, LocalDate nextVacine, String currentMedication, String medicationNotes, LocalDate lastAppointment, LocalDate nextAppointment, String appointmentNotes, LocalDate lastGrooming, String grooming, String notes, Instant moment, Pet pet) {
        this.id = id;
        this.lastVacine = lastVacine;
        this.nextVacine = nextVacine;
        this.currentMedication = currentMedication;
        this.medicationNotes = medicationNotes;
        this.lastAppointment = lastAppointment;
        this.nextAppointment = nextAppointment;
        this.appointmentNotes = appointmentNotes;
        this.lastGrooming = lastGrooming;
        this.grooming = grooming;
        this.notes = notes;
        this.moment = moment;
        this.pet = pet;
    }


}
