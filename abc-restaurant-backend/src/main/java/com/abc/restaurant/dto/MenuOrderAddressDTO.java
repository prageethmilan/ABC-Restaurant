package com.abc.restaurant.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuOrderAddressDTO {
    private String address;
    private String fullName;
    private String mobileNumber;
    private Long restaurantId;
}
