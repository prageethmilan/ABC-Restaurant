package com.abc.restaurant.dto.response;

import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryResponseDTO {
    private Long id;
    private Long mealOrder;
    private Long tableReservation;
    private QueryType queryType;
    private String message;
    private UserDTO user;
    private UserDTO admin;
    private UserDTO staff;
    private UserRole userRole;
    private Date createdDate;
    private Date updatedDate;
    private CommonStatus status;
}
