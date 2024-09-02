package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.TableReservationType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TableReservationRequestDTO {

    private Long restaurantId;
    private String name;
    private String email;
    private String phone;
    private Date date;
    private TableReservationType reservationType;
    private int seats;
    private String note;
}
