package com.abc.restaurant.service;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO) throws UserException, ApplicationException;

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsernameOrEmail(String username, String email) throws UserException;

    UserDTO getUserById(Long id) throws UserException;

    void updateUserStatus(Long id, UserStatus status) throws UserException;

    List<UserDTO> getAllUsersByStatus(UserStatus status);

    void resetUserPassword(String email, int otp, String password) throws UserException;

//    void sendUserOTP(String email);
}
