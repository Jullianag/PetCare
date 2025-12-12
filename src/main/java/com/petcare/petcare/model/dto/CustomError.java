package com.petcare.petcare.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Standard API error response")
public class CustomError {

    @Schema(example = "2025-12-10T15:32:00Z")
    private Instant timeStamp;

    @Schema(example = "404")
    private Integer status;

    @Schema(example = "Resource not found")
    private String error;

    @Schema(example = "/pets/99")
    private String path;

    public CustomError(Instant timeStamp, Integer status, String error, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }
}
