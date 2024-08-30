package com.abc.restaurant.controller;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.exception.UserException;
import com.abc.restaurant.service.UserService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> registerUser(@ModelAttribute UserDTO userDTO) {
        try{
            System.out.println(userDTO.getUsername());
            userService.saveUser(userDTO);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("User registered successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllUsers() {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(userService.getAllUsers())
                        .build(),
                HttpStatus.OK
        );
    }
}
