package com.intern.product.exception;

import com.intern.product.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ApiResponse> handleFileException(
            FileStorageException ex) {

        return ResponseEntity.badRequest()
                .body(new ApiResponse(
                        ex.getMessage()));
    }
}