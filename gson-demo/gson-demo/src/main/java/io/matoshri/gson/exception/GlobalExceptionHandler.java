package io.matoshri.gson.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex,
                                                                HttpServletRequest request) {
        ApiError error = new ApiError(ex.getMessage(), request.getServletPath(), 404, LocalDateTime.now());
        return ResponseEntity.ok(error);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex,
                                                                HttpServletRequest request) {
        ApiError error = new ApiError(ex.getMessage(), request.getServletPath(), 500, LocalDateTime.now());
        return ResponseEntity.ok(error);
    }
}
