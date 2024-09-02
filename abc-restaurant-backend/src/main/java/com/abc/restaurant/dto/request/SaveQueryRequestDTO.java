package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveQueryRequestDTO {
    private Long id;
    private Long mealOrderId;
    private Long tableReservationId;
    private UserRole userRole;
    private QueryType queryType;
    private String message;
    private Long userId;
    private Long repliedTo;
    private CommonStatus status;
}
