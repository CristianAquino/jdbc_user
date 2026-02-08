package com.jdbc.jdbc_user.Repository;

import com.jdbc.jdbc_user.Model.UserModel;

import java.util.List;

public interface UserRepository {
    public List<UserModel> getAllUsers();
    public UserModel getOneUserById(Integer id);
    public UserModel postUserRegister(UserModel user);
    public UserModel delUserById(Integer id);
}
