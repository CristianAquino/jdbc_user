package com.jdbc.jdbc_user.Controller;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.UserModel;
import com.jdbc.jdbc_user.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> allUsers(){
        List<UserModel> gretting = userService.allUsers();
        return ResponseEntity.ok(gretting);
    }

    @GetMapping("/all/dto")
    public ResponseEntity<List<UserDto>> allUsersDto(){
        List<UserDto> gretting = userService.allUsersDto();
        return ResponseEntity.ok(gretting);
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<UserDto> oneUserById(@PathVariable Integer id){
        UserDto response = userService.oneUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> oneUserRegister(@RequestBody UserModel user){
        UserDto register = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @PutMapping("/upd/{id}")
    public String oneUpdateById(@PathVariable Integer id){
        return "oneUpdateById";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> oneDeleteById(@PathVariable Integer id){
        UserDto del = userService.delUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(del);
    }
}
