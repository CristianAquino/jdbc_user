package com.jdbc.jdbc_user.Service.Impl;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.UserModel;
import com.jdbc.jdbc_user.Repository.UserRepository;
import com.jdbc.jdbc_user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserModel> allUsers() {
        List<UserModel> users = userRepository.getAllUsers();
        return users;
    }

    public List<UserDto> allUsersDto(){
        List<UserModel> users = userRepository.getAllUsers();
        List<UserDto> usersDto = transformListDataDto(users);
        return usersDto;
    }

    @Override
    public UserDto oneUserById(Integer id) {
        UserModel user = userRepository.getOneUserById(id);
        UserDto userDto = transformOneDataDto(user);
        return userDto;
    }

    @Transactional
    @Override
    public UserDto createUser(UserModel user) {
        UserModel register = userRepository.postUserRegister(user);
        UserDto dto = transformOneDataDto(register);
        return dto;
    }

    @Transactional
    @Override
    public UserDto delUserById(Integer id) {
        UserModel del = userRepository.delUserById(id);
        UserDto dto = transformOneDataDto(del);
        return dto;
    }

    public static List<UserDto> transformListDataDto(List<UserModel> list){
        List<UserDto> dto = new ArrayList<>();
        for (UserModel user : list){
            dto.add(new UserDto(user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getCountry()));
        }
        return dto;
    }

    public static UserDto transformOneDataDto(UserModel user){
        UserDto dto = new UserDto(user.getUsername(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getCountry());
        return dto;
    }
}
