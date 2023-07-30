package io.matoshri.gson.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiError {

    private String message;
    private String path;
    private int error;
    private LocalDateTime timestamp;

    public ApiError() {}

    public ApiError(String message, String path, int error, LocalDateTime timestamp) {
        this.message = message;
        this.path = path;
        this.error = error;
        this.timestamp = timestamp;
    }
}
