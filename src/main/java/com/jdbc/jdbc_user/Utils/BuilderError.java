package com.jdbc.jdbc_user.Utils;

import com.jdbc.jdbc_user.Model.Response.ApiErrorResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class BuilderError {
    public static ApiErrorResponse response(
            HttpStatus status,
            String errorCode,
            String message,
            String path,
            Map<String, Object> errors) {

        ApiErrorResponse response = new ApiErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(errorCode);
        response.setMessage(message);
        response.setPath(path);
        response.setErrors(errors);

        return response;
    }
}
