package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.TableBookingStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TableResponseDTO {
    private Long id;
    private String tableCode;
    private Integer seatLimit;
    private String category;
    private Long restaurant;
    private TableBookingStatus availability;
    private CommonStatus status;
}
