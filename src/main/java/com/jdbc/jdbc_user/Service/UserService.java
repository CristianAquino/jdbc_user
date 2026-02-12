package com.jdbc.jdbc_user.Service;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.Request.UserRequest;
import com.jdbc.jdbc_user.Model.UserModel;

import java.util.List;

public interface UserService {
    public List<UserModel> allUsers(Integer is_soft);
    public List<UserDto> allUsersDto(Integer is_soft);
    public UserDto oneUserById(Integer id);
    public UserDto createUser(UserRequest user);
    public UserDto delUserById(Integer id);
}
