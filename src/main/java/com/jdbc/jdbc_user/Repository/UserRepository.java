package com.jdbc.jdbc_user.Repository;

import com.jdbc.jdbc_user.Model.Request.UserRequest;
import com.jdbc.jdbc_user.Model.UserModel;

import java.util.List;

public interface UserRepository {
    public List<UserModel> getAllUsers(Integer is_soft);
    public UserModel getOneUserById(Integer id);
    public UserModel postUserRegister(UserRequest user);
    public UserModel delUserById(Integer id);
}
