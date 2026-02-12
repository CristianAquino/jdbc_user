package com.jdbc.jdbc_user.Utils;

import com.jdbc.jdbc_user.Model.Response.ApiResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ResponseBuilder {

    public static<T> ApiResponse<T> response(
            T data,
            HttpStatus status,
            String path
    ){
        ApiResponse<T> response = new ApiResponse<>();

        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setMessage("Operación realizada correctamente");
        response.setPath(path);
        response.setData(data);

        return response;
    }
}
