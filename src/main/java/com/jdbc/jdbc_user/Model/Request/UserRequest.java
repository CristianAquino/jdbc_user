package com.jdbc.jdbc_user.Model.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotNull(message = "Debe llenar este campo")
    @Size(min = 4,max = 100, message = "Debe ingresar por lo menos 4 caracteres")
    private String username;
    @NotNull(message = "Debe llenar este campo")
    @Pattern(regexp = "/{8}$/", message = "Debe ingresar 8 caracteres")
    private String contrasena;
    @NotNull(message = "Debe llenar este campo")
    @Size(min = 4,max = 100, message = "Debe ingresar por lo menos 4 caracteres")
    private String ap_paterno;
    @NotNull(message = "Debe llenar este campo")
    @Size(min = 4,max = 100, message = "Debe ingresar por lo menos 4 caracteres")
    private String ap_materno;
    @NotBlank(message = "Debe llenar este campo")
    @Size(min = 3,max = 100, message = "Debe ingresar por lo menos 4 caracteres")
    private String pais;
}
