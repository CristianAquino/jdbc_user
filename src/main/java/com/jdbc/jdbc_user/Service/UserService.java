package com.jdbc.jdbc_user.Service;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.UserModel;

import java.util.List;

public interface UserService {
    public List<UserModel> allUsers();
    public List<UserDto> allUsersDto();
    public UserDto oneUserById(Integer id);
    public UserDto createUser(UserModel user);
    public UserDto delUserById(Integer id);
}
