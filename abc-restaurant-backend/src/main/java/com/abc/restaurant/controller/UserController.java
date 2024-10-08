package com.abc.restaurant.controller;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.ApplicationException;
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
            System.out.println(userDTO.toString());
            System.out.println("User save process");

            userService.saveUser(userDTO);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("User registered successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (ApplicationException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        List<UserResponseDTO> userList = new ArrayList<>(Collections.emptyList());
        allUsers.forEach(userDTO -> {
            userList.add(new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getNic(), userDTO.getPhoneNumber(), userDTO.getHomeAddress(),userDTO.getUserRole(), userDTO.getUserStatus(), userDTO.getCreatedDate()));
        });
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(userList)
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
                            .message("Either username or email must be provided")
                            .data(null)
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            UserDTO userDTO = userService.getUserByUsernameOrEmail(username,email);
            UserResponseDTO dto = null;
            if (userDTO != null)
                dto = new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getNic(),userDTO.getPhoneNumber(),userDTO.getHomeAddress(),userDTO.getUserRole(), userDTO.getUserStatus(), userDTO.getCreatedDate());
            if (dto != null) {
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(true)
                                .message("User found")
                                .data(dto)
                                .build(),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(false)
                                .message("User not found")
                                .data(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
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
            userResponseDTO = new UserResponseDTO(userById.getId(), userById.getName(), userById.getEmail(), userById.getNic(), userById.getPhoneNumber(), userById.getHomeAddress(), userById.getUserRole(), userById.getUserStatus(), userById.getCreatedDate());
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("")
                            .data(userResponseDTO)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
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
                            .message("User deleted successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
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
                users.add(new UserResponseDTO(userDTO.getId(), userDTO.getName(), userDTO.getEmail(), userDTO.getNic(), userDTO.getPhoneNumber(), userDTO.getHomeAddress(),userDTO.getUserRole(), userDTO.getUserStatus(), userDTO.getCreatedDate()));
            });
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("operation successful")
                            .data(users)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
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
                            .message("User deactivated successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
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
                            .message("User activated successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
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
                                .message("Please enter user email")
                                .data(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            if (!validator.isValidEmail(email))
                return new ResponseEntity<>(
                        CommonResponseUtil.builder()
                                .success(false)
                                .message("Please enter valid user email")
                                .data(null)
                                .build(),
                        HttpStatus.NOT_FOUND
                );
            userService.resetUserPassword(email, requestDTO.getOtp(), requestDTO.getPassword());

            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("Password reset successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e ) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (UserException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
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
