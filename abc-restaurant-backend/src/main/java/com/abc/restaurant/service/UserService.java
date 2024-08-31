package com.abc.restaurant.service;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.exception.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO) throws UserException;
    List<UserDTO> getAllUsers();
    UserDTO getUserByUsernameOrEmail(String username, String email) throws UserException;
}
