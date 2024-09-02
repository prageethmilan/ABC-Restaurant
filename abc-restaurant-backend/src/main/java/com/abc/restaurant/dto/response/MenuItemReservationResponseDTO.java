package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuOrderOperationalStatus;
import com.abc.restaurant.enums.MenuOrderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MenuItemReservationResponseDTO {
    private Long id;
    private String orderId;
    private MenuOrderOperationalStatus operationalStatus;
    private CommonStatus status;
    private MenuOrderType mealOrderType;
    private Long userEntity;
    private Long restaurant;
    private Date createdDate;
    private Date updatedDate;
    private Double total;
    private List<MenuItemResponseDTO> items;
}
