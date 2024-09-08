package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.SaveMenuItemRequestDTO;
import com.abc.restaurant.dto.response.MenuItemCommonResponseDTO;
import com.abc.restaurant.dto.response.MenuItemResDTO;
import com.abc.restaurant.dto.response.MenuItemResponseDTO;
import com.abc.restaurant.dto.response.RestaurantResponseDTO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuServiceImpl implements MenuService {

    private static final Logger log = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public List<MenuItemResDTO> getAllMenus() {
        List<MenuItemResponseDTO> itemList = modelMapper.map(menuRepo.findAll(), new TypeToken<List<MenuItemResponseDTO>>(){}.getType());

        List<MenuItemResDTO> menuItemResDTOS = new ArrayList<>();

        itemList.forEach(item -> {
            RestaurantResponseDTO restaurantResponseDTO = null;
            if (item.getRestaurantId() != 0) {
                Optional<Restaurant> restaurant = restaurantRepo.findById(item.getRestaurantId());
                if (restaurant.get().getStatus().equals(CommonStatus.DELETED)) {
                    try {
                        throw new ApplicationException(200, false,"Restaurant not found");
                    } catch (ApplicationException e) {
                        throw new RuntimeException(e);
                    }
                }
                restaurantResponseDTO = RestaurantResponseDTO.builder()
                        .id(restaurant.get().getId())
                        .name(restaurant.get().getName())
                        .build();
            }
            menuItemResDTOS.add(
                    MenuItemResDTO.builder()
                            .id(item.getId())
                            .restaurant(restaurantResponseDTO)
                            .name(item.getName())
                            .image(item.getImage())
                            .description(item.getDescription())
                            .price(item.getPrice())
                            .qty(item.getQty())
                            .discount(item.getDiscount())
                            .rating(item.getRating())
                            .subCategory(item.getSubCategory())
                            .mainCategory(item.getMainCategory())
                            .menuType(item.getMenuType())
                            .status(item.getStatus())
                            .createdDate(item.getCreatedDate())
                            .updatedDate(item.getUpdatedDate())
                            .build()
            );
        });

        return menuItemResDTOS;
    }

    @Override
    public void saveMenuItem(SaveMenuItemRequestDTO saveMenuItemRequestDTO) throws IOException, ApplicationException {
        try {

            Optional<Restaurant> restaurant = restaurantRepo.findById(saveMenuItemRequestDTO.getRestaurantId());

            if (!restaurant.isPresent() || restaurant.get().getStatus().equals(CommonStatus.DELETED)){
                throw new ApplicationException(200, false, "Restaurant not found");
            }

            String fileUrl;

            if (saveMenuItemRequestDTO.getImg() != null){
                String projectPath = String.valueOf(new File("E:\\Github Projects\\ABC-Restaurant\\abc-restaurant-frontend\\savedImages"));
                File uploadsDir = new File(projectPath + "\\MenuItems");
                uploadsDir.mkdir();
                saveMenuItemRequestDTO.getImg().transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + saveMenuItemRequestDTO.getImg().getOriginalFilename()));
                fileUrl = projectPath + "\\MenuItems\\" + saveMenuItemRequestDTO.getImg().getOriginalFilename();
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
        } catch (ApplicationException e) {
            throw e;
        }
    }

    @Override
    public Object getMenuItemById(Long id) throws ApplicationException {
        try {
            Optional<MenuItem> menuItem = menuRepo.findById(id);

            if (menuItem.isPresent()){
                return mapMenuItemCommonResponseDTO(menuItem.get());
            }

            throw new ApplicationException(404, false, "Meal not found");
        } catch (Exception e) {
            throw e;
        } catch (ApplicationException e) {
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
