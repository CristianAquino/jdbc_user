package com.jdbc.jdbc_user.Service.Impl;

import com.jdbc.jdbc_user.Exception.BusinessException;
import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.Request.UserRequest;
import com.jdbc.jdbc_user.Model.UserModel;
import com.jdbc.jdbc_user.Repository.UserRepository;
import com.jdbc.jdbc_user.Service.UserService;
import com.jdbc.jdbc_user.Utils.CleanErroResponse;
import com.jdbc.jdbc_user.Utils.TransformDataUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private CleanErroResponse cleanErroResponse;
    private TransformDataUserDto transformDataUserDto;

    @Override
    public List<UserModel> allUsers(Integer is_soft) {
        try{
            List<UserModel> users = userRepository.getAllUsers(is_soft);
            return users;
        }catch (DataAccessException e){
            SQLException sqlEx = (SQLException) e.getCause();
            throw new BusinessException(
                    sqlEx.getErrorCode(),
                    cleanErroResponse.transform(sqlEx.getMessage())
            );
        }

    }

    public List<UserDto> allUsersDto(Integer is_soft){
        try{
            List<UserModel> users = userRepository.getAllUsers(is_soft);
            List<UserDto> usersDto = transformDataUserDto.transformListDataDto(users);
            return usersDto;
        }catch (DataAccessException e){
            SQLException sqlEx = (SQLException) e.getCause();
            throw new BusinessException(
                    sqlEx.getErrorCode(),
                    cleanErroResponse.transform(sqlEx.getMessage())
            );
        }
    }

    @Override
    public UserDto oneUserById(Integer id) {
        try{
            UserModel user = userRepository.getOneUserById(id);
            UserDto userDto = transformDataUserDto.transformOneDataDto(user);
            return userDto;
        }catch (DataAccessException e){
            SQLException sqlEx = (SQLException) e.getCause();
            throw new BusinessException(
                    sqlEx.getErrorCode(),
                    cleanErroResponse.transform(sqlEx.getMessage())
            );
        }
    }

    @Transactional
    @Override
    public UserDto createUser(UserRequest user) {
        try{
            UserModel register = userRepository.postUserRegister(user);
            UserDto dto = transformDataUserDto.transformOneDataDto(register);
            return dto;
        }catch (DataAccessException e){
            SQLException sqlEx = (SQLException) e.getCause();
            throw new BusinessException(
                    sqlEx.getErrorCode(),
                    cleanErroResponse.transform(sqlEx.getMessage())
            );
        }
    }

    @Transactional
    @Override
    public UserDto delUserById(Integer id) {
        try{
            UserModel del = userRepository.delUserById(id);
            UserDto dto = transformDataUserDto.transformOneDataDto(del);
            return dto;
        }catch(DataAccessException e){
            SQLException sqlEx = (SQLException) e.getCause();
            throw new BusinessException(
                    sqlEx.getErrorCode(),
                    cleanErroResponse.transform(sqlEx.getMessage())
            );
        }
    }
}