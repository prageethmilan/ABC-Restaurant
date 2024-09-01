package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuMainCategory;
import com.abc.restaurant.enums.MenuSubCategory;
import com.abc.restaurant.enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemCommonResponseDTO {
    private Long id;
    private Long restaurantId;
    private String name;
    private MenuMainCategory mainCategory;
    private MenuSubCategory subCategory;
    private MenuType menuType;
    private Float price;
    private Float discount;
    private CommonStatus status;
    private Long rating;
    private String description;
    private String img;
    private Date createdDate;
    private Date updatedDate;
}
