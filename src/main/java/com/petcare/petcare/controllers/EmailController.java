package com.petcare.petcare.controllers;

import com.petcare.petcare.model.dto.EmailDTO;
import com.petcare.petcare.services.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
@Tag(name = "Emails", description = "Endpoints for email sending and notifications")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(
            summary = "Send an email",
            description = "Send an email using the configured email service",
            responses = {
                    @ApiResponse(description = "Email sent successfully", responseCode = "204"),
                    @ApiResponse(description = "Bad Request", responseCode = "400"),
                    @ApiResponse(description = "Unauthorized", responseCode = "401"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
                    @ApiResponse(description = "Unprocessable Entity", responseCode = "422"),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500")
            }
    )
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> send(@Valid @RequestBody EmailDTO dto) {
        emailService.sendEmail(dto);
        return ResponseEntity.noContent().build();
    }
}
