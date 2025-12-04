package com.petcare.petcare.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.petcare.petcare.model.entities.CareSchedule;

import java.time.LocalDate;

public class CareScheduleMinDTO {

    private Long petId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate nextVacine;

    private String currentMedication;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate nextAppointment;
    private String grooming;

    public CareScheduleMinDTO() {
    }

    public CareScheduleMinDTO(Long petId, LocalDate nextVacine, String currentMedication, LocalDate nextAppointment, String grooming) {
        this.petId = petId;
        this.nextVacine = nextVacine;
        this.currentMedication = currentMedication;
        this.nextAppointment = nextAppointment;
        this.grooming = grooming;
    }

    public CareScheduleMinDTO(CareSchedule entity) {
        this.petId = entity.getPet().getId();
        this.nextVacine = entity.getNextVacine();
        this.currentMedication = entity.getCurrentMedication();
        this.nextAppointment = entity.getNextAppointment();
        this.grooming = entity.getGrooming();
    }

    public Long getPetId() {
        return petId;
    }

    public LocalDate getNextVacine() {
        return nextVacine;
    }

    public String getCurrentMedication() {
        return currentMedication;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public String getGrooming() {
        return grooming;
    }

}
