package com.jdbc.jdbc_user.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;
    private T data;
}
