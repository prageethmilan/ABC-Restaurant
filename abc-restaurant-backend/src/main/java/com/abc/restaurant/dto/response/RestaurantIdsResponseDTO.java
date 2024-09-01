package com.abc.restaurant.dto.response;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantIdsResponseDTO {
    private Long value;
    private String label;
}
