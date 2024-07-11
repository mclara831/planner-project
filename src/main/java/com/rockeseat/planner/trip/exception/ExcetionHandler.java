package com.rockeseat.planner.trip.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExcetionHandler {
    
    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<StandartError> dateTime(DateTimeException exception, HttpServletRequest request) {
        String error = "Data time error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StandartError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StandartError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI()));
    }
}
