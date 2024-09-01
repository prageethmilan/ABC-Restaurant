package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveAdminRequestDTO;

import java.util.List;

public interface AdminService {
    void saveAdmin(SaveAdminRequestDTO saveAdminRequestDTO);

    List<Object> getAllAdminPortalUsers();

    Object findAdminPortalUserByEmail(String email);
}
