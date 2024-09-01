package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.SaveRestaurantRequestDTO;
import com.abc.restaurant.dto.response.RestaurantIdsResponseDTO;
import com.abc.restaurant.dto.response.RestaurantResponseDTO;
import com.abc.restaurant.entity.Restaurant;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.repository.RestaurantRepo;
import com.abc.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return modelMapper.map(restaurantRepo.findAll(), new TypeToken<List<RestaurantResponseDTO>>(){}.getType());
    }

    @Override
    public List<RestaurantIdsResponseDTO> getAllRestaurantIds() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        List<RestaurantIdsResponseDTO> restaurantIdsResponseDTOS = new ArrayList<>();

        restaurants.forEach((item) -> {
            RestaurantIdsResponseDTO restaurantIdsResponseDTO = RestaurantIdsResponseDTO.builder()
                    .value(item.getId())
                    .label(item.getName())
                    .build();
            restaurantIdsResponseDTOS.add(restaurantIdsResponseDTO);
        });

        return restaurantIdsResponseDTOS;
    }

    @Override
    public void saveRestaurant(SaveRestaurantRequestDTO saveRestaurantRequestDTO) throws ApplicationException {
        try {
            if (saveRestaurantRequestDTO.getId() == 0) {
                Restaurant newRestaurant = Restaurant.builder()
                        .name(saveRestaurantRequestDTO.getName())
                        .email(saveRestaurantRequestDTO.getEmail())
                        .address(saveRestaurantRequestDTO.getAddress())
                        .branchCode(saveRestaurantRequestDTO.getBranchCode())
                        .phone(saveRestaurantRequestDTO.getPhone())
                        .status(saveRestaurantRequestDTO.getStatus())
                        .build();

                restaurantRepo.save(newRestaurant);
            } else {
                Optional<Restaurant> restaurant = restaurantRepo.findById(saveRestaurantRequestDTO.getId());

                if (!restaurant.isPresent()) {
                    throw new ApplicationException(200, false, "Sorry required restaurant  not found");
                }

                Restaurant existingRestaurant = restaurant.get();

                existingRestaurant.setName(saveRestaurantRequestDTO.getName());
                existingRestaurant.setEmail(saveRestaurantRequestDTO.getEmail());
                existingRestaurant.setAddress(saveRestaurantRequestDTO.getAddress());
                existingRestaurant.setBranchCode(saveRestaurantRequestDTO.getBranchCode());
                existingRestaurant.setPhone(saveRestaurantRequestDTO.getPhone());
                existingRestaurant.setUpdatedDate(new Date());
                existingRestaurant.setStatus(saveRestaurantRequestDTO.getStatus());

                restaurantRepo.save(existingRestaurant);
            }
        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
            throw e;
        }
    }

    @Override
    public Object getRestaurantById(Long id) throws ApplicationException {
        try {
            Optional<Restaurant> restaurant = restaurantRepo.findById(id);

            if (restaurant.isPresent()) {
                return mapRestaurantResponseDTO(restaurant.get());
            }
            throw new ApplicationException(404, false, "restaurant not found");
        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
            throw e;
        }
    }

    private RestaurantResponseDTO mapRestaurantResponseDTO(Restaurant restaurant) {
        return RestaurantResponseDTO.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .email(restaurant.getEmail())
                .phone(restaurant.getPhone())
                .address(restaurant.getAddress())
                .branchCode(restaurant.getBranchCode())
                .status(restaurant.getStatus())
                .createdDate(restaurant.getCreatedDate())
                .updatedDate(restaurant.getUpdatedDate())
                .build();
    }
}
