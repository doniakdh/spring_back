package com;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ApiError {
    private String message;
    private int code;
    private LocalDateTime timestamp;
}
