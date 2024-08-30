package com.abc.restaurant.service;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.exception.UserException;

public interface UserService {
    void saveUser(UserDTO userDTO) throws UserException;
}
