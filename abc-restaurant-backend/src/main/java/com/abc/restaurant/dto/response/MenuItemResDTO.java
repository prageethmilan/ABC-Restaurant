package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuMainCategory;
import com.abc.restaurant.enums.MenuSubCategory;
import com.abc.restaurant.enums.MenuType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class MenuItemResDTO {

    private Long id;
    private RestaurantResponseDTO restaurant;
    private String name;
    private String image;
    private String description;
    private Float price;
    private Float qty;
    private Float discount;
    private Long rating;
    private MenuSubCategory subCategory;
    private MenuMainCategory mainCategory;
    private MenuType menuType;
    private CommonStatus status;
    private Date createdDate;
    private Date updatedDate;
}
