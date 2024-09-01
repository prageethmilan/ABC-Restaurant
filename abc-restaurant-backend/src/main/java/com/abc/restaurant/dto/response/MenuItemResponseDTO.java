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
public class MenuItemResponseDTO {

    private Long id;
    private Long restaurantId;
    private String name;
    private String image;
    private String description;
    private Float price;
    private Float qty;
    private Float discount;
    private Long rating;
    private MenuSubCategory subCategory;
    private MenuMainCategory mainCategory;
    private MenuType mealType;
    private CommonStatus status;
    private Date createdDate;
    private Date updatedDate;
}
