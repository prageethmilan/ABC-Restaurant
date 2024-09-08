package com.abc.restaurant.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class MenuItemFilterResponseDTO {

    private Long restaurantId;
    private List<MenuItemResDTO> meals = new ArrayList<>();
}
