package com.jdbc.jdbc_user.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id_usua;
    private String username;
    private String contrasena;
    private String ap_paterno;
    private String ap_materno;
    private String pais;
}
