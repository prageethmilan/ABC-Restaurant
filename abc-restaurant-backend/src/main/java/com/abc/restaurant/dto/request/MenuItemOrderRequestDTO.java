package com.abc.restaurant.dto.request;

import com.abc.restaurant.dto.MenuOrderAddressDTO;
import com.abc.restaurant.enums.MenuOrderType;
import lombok.*;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MenuItemOrderRequestDTO {
    private Long restaurantId;
    private boolean isDiffAddress;
    private MenuOrderType orderType;
    private ArrayList<MenuOrderItemRequest> items = new ArrayList<>();
    private MenuOrderAddressDTO address;
}
