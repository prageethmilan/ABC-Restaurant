package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveRestaurantRequestDTO;
import com.abc.restaurant.dto.response.RestaurantIdsResponseDTO;
import com.abc.restaurant.dto.response.RestaurantResponseDTO;
import com.abc.restaurant.exception.ApplicationException;

import java.util.List;

public interface RestaurantService {
    List<RestaurantResponseDTO> getAllRestaurants();

    List<RestaurantIdsResponseDTO> getAllRestaurantIds();

    void saveRestaurant(SaveRestaurantRequestDTO saveRestaurantRequestDTO) throws ApplicationException;

    Object getRestaurantById(Long id) throws ApplicationException;
}
