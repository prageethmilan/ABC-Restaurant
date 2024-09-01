package com.abc.restaurant.controller;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.UserException;
import com.abc.restaurant.service.UserService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import com.abc.restaurant.dto.request.UserResetPasswordRequestDTO;
import com.abc.restaurant.dto.response.UserResponseDTO;
import com.abc.restaurant.util.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final Validator validator;

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
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        List<UserResponseDTO> userList = new ArrayList<>(Collections.emptyList());
        allUsers.forEach(userDTO -> {
            userList.add(new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserRole(), userDTO.getUserStatus()));
        });
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(userList)
                        .build(),
                HttpStatus.OK
        );
    }


    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getUserByUsernameOrEmail(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "email", required = false) String email) {
        if ((username == null || username.isEmpty()) && (email == null || email.isEmpty())) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("Either username or email must be provided")
                            .body(null)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            UserDTO userDTO = userService.getUserByUsernameOrEmail(username,email);
            UserResponseDTO dto = null;
            if (userDTO != null)
                dto = new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserRole(), userDTO.getUserStatus());
            if (dto != null) {
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(true)
                                .msg("User found")
                                .body(dto)
                                .build(),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(false)
                                .msg("User not found")
                                .body(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getUserByUserId(@PathVariable Long id) throws UserException {
        try {
            UserDTO userById = userService.getUserById(id);
            UserResponseDTO userResponseDTO = null;
            userResponseDTO = new UserResponseDTO(userById.getId(), userById.getName(), userById.getUsername(), userById.getEmail(), userById.getUserRole(), userById.getUserStatus());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("")
                            .body(userResponseDTO)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> deleteUser(@PathVariable Long id) {
        try {
            userService.updateUserStatus(id, UserStatus.DELETED);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("User deleted successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllUsersByStatus(@RequestParam(value = "status") UserStatus status) {
        try {
            List<UserResponseDTO> users = new ArrayList<>(Collections.emptyList());
            List<UserDTO> allUsersByStatus = userService.getAllUsersByStatus(status);
            allUsersByStatus.forEach(userDTO -> {
                users.add(new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(), userDTO.getUserRole(), userDTO.getUserStatus()));
            });
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("operation successful")
                            .body(users)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(value = "/deactivate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> deactivateUser(@PathVariable Long id) {
        try {
            userService.updateUserStatus(id, UserStatus.INACTIVE);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("User deactivated successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping(value = "/activate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> activateUser(@PathVariable Long id) {
        try {
            userService.updateUserStatus(id, UserStatus.ACTIVE);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("User activated successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @PutMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> resetUserPassword(@RequestBody(required = false) UserResetPasswordRequestDTO requestDTO, @RequestParam(value = "email", required = false) String email) throws UserException {
        try {
            if (!email.trim().isEmpty())
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(false)
                                .msg("Please enter user email")
                                .body(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            if (!validator.isValidEmail(email))
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(false)
                                .msg("Please enter valid user email")
                                .body(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            userService.resetUserPassword(email, requestDTO.getOtp(), requestDTO.getPassword());

            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("Password reset successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e ) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

//    @PostMapping(value = "/otp/send")
//    public ResponseEntity<CommonResponseUtil> sendUserOTP(@RequestParam(value = "email") String email) {
//        userService.sendUserOTP(email);
//        return new ResponseEntity<>(
//                CommonResponseUtil.builder()
//                        .success(true)
//                        .msg("OTP has been sent successfully to " + email)
//                        .body(null)
//                        .build()
//                , HttpStatus.OK);
//    }
}
