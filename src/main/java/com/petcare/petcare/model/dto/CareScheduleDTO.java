package com.petcare.petcare.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petcare.petcare.model.entities.CareSchedule;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;

import java.time.Instant;
import java.time.LocalDate;

public class CareScheduleDTO {

    @Schema(description = "Database generated pet ID")
    private Long id;

    @PastOrPresent(message = "Last vacination date must be in the past or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lastVacine;

    @FutureOrPresent(message = "Next vacination date must be in the future or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate nextVacine;

    private String currentMedication;
    private String medicationNotes;

    @PastOrPresent(message = "Last appointment date must be in the past or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lastAppointment;

    @FutureOrPresent(message = "Next appointment date must be in the future or present.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate nextAppointment;

    @Column(columnDefinition = "TEXT")
    private String appointmentNotes;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate lastGrooming;
    private String grooming;
    private String notes;

    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = "dd/MM/yyyy HH:mm:ss",
            timezone = "America/Sao_Paulo")

    private PetMinDTO petMinDTO;

    public CareScheduleDTO(Long id, LocalDate lastVacine, LocalDate nextVacine, String currentMedication,
                           String medicationNotes, LocalDate lastAppointment, LocalDate nextAppointment,
                           String appointmentNotes, LocalDate lastGrooming, String grooming, String notes) {
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
    }

    public CareScheduleDTO(CareSchedule entity) {
        this.id = entity.getId();
        this.lastVacine = entity.getLastVacine();
        this.nextVacine = entity.getNextVacine();
        this.currentMedication = entity.getCurrentMedication();
        this.medicationNotes = entity.getMedicationNotes();
        this.lastAppointment = entity.getLastAppointment();
        this.nextAppointment = entity.getNextAppointment();
        this.appointmentNotes = entity.getAppointmentNotes();
        this.lastGrooming = entity.getLastGrooming();
        this.grooming = entity.getGrooming();
        this.notes = entity.getNotes();

        if (entity.getPet() != null) {
            this.petMinDTO = new PetMinDTO(entity.getPet());
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDate getLastVacine() {
        return lastVacine;
    }

    public LocalDate getNextVacine() {
        return nextVacine;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public String getMedicationNotes() {
        return medicationNotes;
    }

    public LocalDate getLastAppointment() {
        return lastAppointment;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public String getAppointmentNotes() {
        return appointmentNotes;
    }

    public LocalDate getLastGrooming() {
        return lastGrooming;
    }

    public String getGrooming() {
        return grooming;
    }

    public String getNotes() {
        return notes;
    }

    public PetMinDTO getPetMinDTO() {
        return petMinDTO;
    }
}
