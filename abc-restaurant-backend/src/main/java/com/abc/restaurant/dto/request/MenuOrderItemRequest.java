package com.abc.restaurant.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MenuOrderItemRequest {
    private Long id;
    private Float qty;
}
