package java_jabki.x6_product.controller;

import java_jabki.x6_product.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllException(final Exception badRequestException) {
        return ResponseEntity.badRequest().body(new ApiError(false, badRequestException.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String errorMessage = "JSON parsing error: " + ex.getMessage();
        return ResponseEntity.badRequest().body(new ApiError(false, errorMessage));
    }
}
