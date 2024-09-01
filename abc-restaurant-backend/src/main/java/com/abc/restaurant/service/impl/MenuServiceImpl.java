package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.SaveMenuItemRequestDTO;
import com.abc.restaurant.dto.response.MenuItemCommonResponseDTO;
import com.abc.restaurant.dto.response.MenuItemResponseDTO;
import com.abc.restaurant.entity.MenuItem;
import com.abc.restaurant.entity.Restaurant;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.repository.MenuRepo;
import com.abc.restaurant.repository.RestaurantRepo;
import com.abc.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public List<MenuItemResponseDTO> getAllMenus() {
        return modelMapper.map(menuRepo.findAll(), new TypeToken<List<MenuItemResponseDTO>>(){}.getType());
    }

    @Override
    public void saveMenuItem(SaveMenuItemRequestDTO saveMenuItemRequestDTO) throws IOException {
        try {
            Optional<Restaurant> restaurant = restaurantRepo.findById(saveMenuItemRequestDTO.getRestaurantId());
            if (!restaurant.isPresent() || restaurant.get().getStatus().equals(CommonStatus.DELETED)){
                throw new ApplicationException(200, false, "Restaurant not found");
            }

            String fileUrl;

            if (!saveMenuItemRequestDTO.getImg().isEmpty() && saveMenuItemRequestDTO.getImg() != null){
                String projectPath = String.valueOf(new File("E:\\Github Projects\\ABC-Restaurant\\abc-restaurant-frontend\\savedImages"));
                File uploadsDir = new File(projectPath + "\\MenuItems");
                uploadsDir.mkdir();
                saveMenuItemRequestDTO.getImg().transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + saveMenuItemRequestDTO.getImg().getOriginalFilename()));
                fileUrl = projectPath + "\\MenuItems\\" + saveMenuItemRequestDTO.getImg().getOriginalFilename().replace("", "_") + "_" + new Date().getTime();
            } else {
                fileUrl = null;
            }

            if (saveMenuItemRequestDTO.getId() == 0) {
                MenuItem newMenuItem = MenuItem.builder()
                        .name(saveMenuItemRequestDTO.getName())
                        .image(fileUrl)
                        .description(saveMenuItemRequestDTO.getDescription())
                        .price(saveMenuItemRequestDTO.getPrice())
                        .discount(saveMenuItemRequestDTO.getDiscount())
                        .rating(saveMenuItemRequestDTO.getRating())
                        .subCategory(saveMenuItemRequestDTO.getSubCategory())
                        .mainCategory(saveMenuItemRequestDTO.getMainCategory())
                        .menuType(saveMenuItemRequestDTO.getMenuType())
                        .restaurant(restaurant.get())
                        .status(saveMenuItemRequestDTO.getStatus())
                        .build();
                menuRepo.save(newMenuItem);
            } else {
                Optional<MenuItem> menuItem = menuRepo.findById(saveMenuItemRequestDTO.getId());

                if (!menuItem.isPresent()) {
                    throw new ApplicationException(200, false, "Sorry required meal  not found");
                }

                MenuItem existingMenuItem = menuItem.get();

                if (fileUrl == null) {
                    fileUrl = existingMenuItem.getImage();
                }

                existingMenuItem.setName(saveMenuItemRequestDTO.getName());
                existingMenuItem.setImage(fileUrl);
                existingMenuItem.setDescription(saveMenuItemRequestDTO.getDescription());
                existingMenuItem.setPrice(saveMenuItemRequestDTO.getPrice());
                existingMenuItem.setDiscount(saveMenuItemRequestDTO.getDiscount());
                existingMenuItem.setRating(saveMenuItemRequestDTO.getRating());
                existingMenuItem.setSubCategory(saveMenuItemRequestDTO.getSubCategory());
                existingMenuItem.setMainCategory(saveMenuItemRequestDTO.getMainCategory());
                existingMenuItem.setMenuType(saveMenuItemRequestDTO.getMenuType());
                existingMenuItem.setRestaurant(restaurant.get());
                existingMenuItem.setStatus(saveMenuItemRequestDTO.getStatus());
                existingMenuItem.setUpdatedDate(new Date());

                menuRepo.save(existingMenuItem);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Object getMenuItemById(Long id) {
        try {
            Optional<MenuItem> menuItem = menuRepo.findById(id);

            if (menuItem.isPresent()){
                return mapMenuItemCommonResponseDTO(menuItem.get());
            }

            throw new ApplicationException(404, false, "Meal not found");
        } catch (Exception e) {
            throw e;
        }
    }

    private MenuItemCommonResponseDTO mapMenuItemCommonResponseDTO(MenuItem menuItem) {
        return MenuItemCommonResponseDTO.builder()
                .id(menuItem.getId())
                .restaurantId(menuItem.getRestaurant().getId())
                .name(menuItem.getName())
                .mainCategory(menuItem.getMainCategory())
                .subCategory(menuItem.getSubCategory())
                .menuType(menuItem.getMenuType())
                .price(menuItem.getPrice())
                .discount(menuItem.getDiscount())
                .status(menuItem.getStatus())
                .rating(menuItem.getRating())
                .description(menuItem.getDescription())
                .img(menuItem.getImage())
                .createdDate(menuItem.getCreatedDate())
                .updatedDate(menuItem.getUpdatedDate())
                .build();
    }
}
