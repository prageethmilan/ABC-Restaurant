package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuMainCategory;
import com.abc.restaurant.enums.MenuSubCategory;
import com.abc.restaurant.enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMenuItemRequestDTO {
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
    private MultipartFile img;
}
