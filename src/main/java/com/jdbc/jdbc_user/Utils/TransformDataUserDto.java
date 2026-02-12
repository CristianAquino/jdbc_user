package com.jdbc.jdbc_user.Utils;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.UserModel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class TransformDataUserDto {
    public static List<UserDto> transformListDataDto(List<UserModel> list){
        List<UserDto> dto = new ArrayList<>();
        for (UserModel user : list){
            dto.add(new UserDto(
                    user.getId_usua(),
                    user.getUsername(),
                    user.getAp_paterno(),
                    user.getAp_materno(),
                    user.getPais()));
        }
        return dto;
    }

    public static UserDto transformOneDataDto(UserModel user){
        UserDto dto = new UserDto(
                user.getId_usua(),
                user.getUsername(),
                user.getAp_paterno(),
                user.getAp_materno(),
                user.getPais());
        return dto;
    }
}
