package com.petcare.petcare.model.entities;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_care_schedule")
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

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    @OneToOne
    @MapsId
    private Pet pet;

    public CareSchedule() {
    }

    public CareSchedule(Long id, LocalDate lastVacine, LocalDate nextVacine, String currentMedication,
                        String medicationNotes, LocalDate lastAppointment, LocalDate nextAppointment,
                        String appointmentNotes, LocalDate lastGrooming, String grooming, String notes,
                        Pet pet) {
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
        this.pet = pet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLastVacine() {
        return lastVacine;
    }

    public void setLastVacine(LocalDate lastVacine) {
        this.lastVacine = lastVacine;
    }

    public LocalDate getNextVacine() {
        return nextVacine;
    }

    public void setNextVacine(LocalDate nextVacine) {
        this.nextVacine = nextVacine;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public void setCurrentMedication(String currentMedication) {
        this.currentMedication = currentMedication;
    }

    public String getMedicationNotes() {
        return medicationNotes;
    }

    public void setMedicationNotes(String medicationNotes) {
        this.medicationNotes = medicationNotes;
    }

    public LocalDate getLastAppointment() {
        return lastAppointment;
    }

    public void setLastAppointment(LocalDate lastAppointment) {
        this.lastAppointment = lastAppointment;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDate nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public String getAppointmentNotes() {
        return appointmentNotes;
    }

    public void setAppointmentNotes(String appointmentNotes) {
        this.appointmentNotes = appointmentNotes;
    }

    public LocalDate getLastGrooming() {
        return lastGrooming;
    }

    public void setLastGrooming(LocalDate lastGrooming) {
        this.lastGrooming = lastGrooming;
    }

    public String getGrooming() {
        return grooming;
    }

    public void setGrooming(String grooming) {
        this.grooming = grooming;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CareSchedule that = (CareSchedule) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
