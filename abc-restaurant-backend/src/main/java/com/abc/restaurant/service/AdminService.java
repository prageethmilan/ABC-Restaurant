package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveAdminRequestDTO;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.exception.UserException;

import java.util.List;

public interface AdminService {
    void saveAdmin(SaveAdminRequestDTO saveAdminRequestDTO) throws ApplicationException, UserException;

    List<Object> getAllAdminPortalUsers();

    Object findAdminPortalUserByEmail(String email) throws ApplicationException;
}
