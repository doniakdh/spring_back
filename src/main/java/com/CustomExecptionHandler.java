package com;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.springPR.PersonNotFoundException;

@ControllerAdvice
public class CustomExecptionHandler {
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ApiError> handlePersonNotFoundException(PersonNotFoundException e){
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
