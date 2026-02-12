package com.jdbc.jdbc_user.Controller;

import com.jdbc.jdbc_user.Model.Dto.UserDto;
import com.jdbc.jdbc_user.Model.Request.UserRequest;
import com.jdbc.jdbc_user.Model.Response.ApiResponse;
import com.jdbc.jdbc_user.Model.UserModel;
import com.jdbc.jdbc_user.Service.UserService;
import com.jdbc.jdbc_user.Utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private ResponseBuilder responseBuilder;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserModel>>> allUsers(
            @RequestParam(required = false)  @Min(0) @Max(1) Integer is_soft,
            HttpServletRequest http){
            List<UserModel> datos = userService.allUsers(is_soft);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBuilder
                        .response(
                                datos,
                                HttpStatus.OK,
                                http.getRequestURI()
                        )
                );
    }

    @GetMapping("/all/dto")
    public ResponseEntity<ApiResponse<List<UserDto>>> allUsersDto(
            @RequestParam(required = false)Integer is_soft,
            HttpServletRequest http){
        List<UserDto> datos = userService.allUsersDto(is_soft);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBuilder
                        .response(
                                datos,
                                HttpStatus.OK,
                                http.getRequestURI()
                        )
                );
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<ApiResponse<UserDto>> oneUserById(
            @PathVariable @Valid @Positive Integer id,
            HttpServletRequest http){
        UserDto datos = userService.oneUserById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseBuilder
                        .response(
                                datos,
                                HttpStatus.OK,
                                http.getRequestURI()
                        ));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> oneUserRegister(
            @RequestBody @Valid UserRequest user,
            HttpServletRequest http){
        UserDto data = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/one/{id}")
                .buildAndExpand(data.getId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(responseBuilder
                        .response(
                                data,
                                HttpStatus.CREATED,
                                http.getRequestURI()
                        )
                );
    }

    @PutMapping("/upd/{id}")
    public String oneUpdateById(@PathVariable Integer id){
        return "oneUpdateById";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<UserDto>> oneDeleteById(
            @PathVariable @Valid @Positive Integer id,
            HttpServletRequest http){
        UserDto data = userService.delUserById(id);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(responseBuilder
                        .response(
                                data,
                                HttpStatus.ACCEPTED,
                                http.getRequestURI()
                        ));
    }
}