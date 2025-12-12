package com.petcare.petcare.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO {

    @NotBlank(message = "fromEmail cannot be blank.")
    @Email(message = "Invalid email format.")
    @Schema(description = "Sender email address")
    private String fromEmail;

    @NotBlank(message = "fromName cannot be blank.")
    @Schema(description = "Sender display name")
    private String fromName;

    @Schema(description = "Reply-to email address")
    private String replyTo;

    @NotBlank(message = "to cannot be blank.")
    @Schema(description = "Recipient email address")
    private String to;

    @Schema(description = "Email subject")
    private String subject;

    @Schema(
            description = "Email body content",
            example = "Olá, este é um e-mail teste."
    )
    private String body;

    @Schema(
            description = "Content type of the email",
            example = "text/plain ou text/html"
    )
    private String contentType;

    public EmailDTO() {
    }

    public EmailDTO(String fromEmail, String fromName, String replyTo, String to, String subject, String body, String contentType) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.replyTo = replyTo;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.contentType = contentType;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
