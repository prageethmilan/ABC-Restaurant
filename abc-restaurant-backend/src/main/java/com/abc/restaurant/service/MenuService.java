package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveMenuItemRequestDTO;
import com.abc.restaurant.dto.response.MenuItemResponseDTO;
import com.abc.restaurant.exception.ApplicationException;

import java.io.IOException;
import java.util.List;

public interface MenuService {
    List<MenuItemResponseDTO> getAllMenus();

    void saveMenuItem(SaveMenuItemRequestDTO saveMenuItemRequestDTO) throws IOException, ApplicationException;

    Object getMenuItemById(Long id) throws ApplicationException;
}
