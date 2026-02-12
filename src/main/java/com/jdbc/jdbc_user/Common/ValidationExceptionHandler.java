package com.jdbc.jdbc_user.Common;

import com.jdbc.jdbc_user.Model.Response.ApiErrorResponse;
import com.jdbc.jdbc_user.Utils.BuilderError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {
    private BuilderError buildError;

    // user request validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleSpringValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.computeIfAbsent(
                            error.getField(),
                            k -> new ArrayList<String>()
                    );
                    ((List<String>) errors.get(error.getField()))
                            .add(error.getDefaultMessage());
                });

        ApiErrorResponse response = buildError.response(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Error de validación en los datos enviados",
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    // en caso no cumpla la condicion min - max
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        Map<String, Object> errors = new HashMap<>();

        ex.getConstraintViolations()
                .forEach(v -> {
                    String path = v.getPropertyPath().toString();
                    String field = path.substring(path.lastIndexOf('.') + 1);

                    errors.computeIfAbsent(field, k -> new ArrayList<String>());
                    ((List<String>) errors.get(field)).add(v.getMessage());
                });

        ApiErrorResponse response = buildError.response(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Error de validación en los datos enviados",
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.badRequest().body(response);
    }

    // en caso no cumpla con el tipo de dato
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest request) {

        Map<String, Object> errors = new HashMap<>();
        errors.put(ex.getName(), List.of("Tipo de dato inválido"));

        ApiErrorResponse response = buildError.response(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_ERROR",
                "Error de validación en los datos enviados",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }


}
