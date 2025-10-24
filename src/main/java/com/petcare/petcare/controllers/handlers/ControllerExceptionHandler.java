package com.petcare.petcare.controllers.handlers;

import com.petcare.petcare.model.dto.CustomError;
import com.petcare.petcare.model.dto.ValidationError;
import com.petcare.petcare.services.exceptions.DatabaseException;
import com.petcare.petcare.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), httpStatus.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(), httpStatus.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(), httpStatus.value(), "Invalid data", request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            validationError.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(validationError);
    }
}
