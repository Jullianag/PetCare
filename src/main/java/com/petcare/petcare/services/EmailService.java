package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.EmailDTO;
import com.petcare.petcare.services.exceptions.EmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final SendGrid sendGrid;

    public EmailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(EmailDTO dto) {

        Email from = new Email(dto.getFromEmail(), dto.getFromName());
        Email to = new Email(dto.getTo());
        Content content = new Content(dto.getContentType(), dto.getBody());
        Mail mail = new Mail(from, dto.getSubject(), to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            LOGGER.info("Sending email to " + to.getEmail());
            Response response = sendGrid.api(request);

            if (response.getStatusCode() >= 400 && response.getStatusCode() <= 500) {
                LOGGER.error("Error sending email: " + response.getBody());
                throw new EmailException("Error sending email: " + response.getBody());
            }

            LOGGER.info("Email send! Status: " + response.getStatusCode());

        } catch (IOException e) {
            throw new EmailException(e.getMessage());
        }
    }
}
