package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveRestaurantRequestDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String branchCode;
    private CommonStatus status;
}
