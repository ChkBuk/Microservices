package com.mic.res.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private String message;
    private String details;
    private LocalDateTime timestamp;
    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public ErrorDetails(String message, String details, LocalDateTime timestamp) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }
}
