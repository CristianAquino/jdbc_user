package com.jdbc.jdbc_user.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
}
