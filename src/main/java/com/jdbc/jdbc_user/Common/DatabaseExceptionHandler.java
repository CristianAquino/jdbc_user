package com.jdbc.jdbc_user.Common;

import com.jdbc.jdbc_user.Exception.BusinessException;
import com.jdbc.jdbc_user.Model.Response.ApiErrorResponse;
import com.jdbc.jdbc_user.Utils.BuilderError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DatabaseExceptionHandler {
    private BuilderError builderError;

    // data base validation
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessExceptionValidation(
            BusinessException ex,
            HttpServletRequest request
    ) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("oracle",ex.getMessage());

        ApiErrorResponse response = builderError.response (
                ex.getCode(),
                "DATABASE_ERROR",
                "Error de validación en BD",
                request.getRequestURI(),
                errors
        );
        return ResponseEntity.status(ex.getCode()).body(response);
    }

}
