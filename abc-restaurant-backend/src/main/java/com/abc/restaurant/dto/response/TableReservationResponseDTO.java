package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.TableReservationOperationalStatus;
import com.abc.restaurant.enums.TableReservationType;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TableReservationResponseDTO {
    private Long id;
    private String reservationCode;
    private Integer max_count;
    private Date reservedDate;
    private CommonStatus status;
    private Long approvedBy;
    private String approvedNote;
    private String customerNote;
    private TableReservationType tableReservationType;
    private TableReservationOperationalStatus operationalStatus;
    private Date createdDate;
    private Date updatedDate;
}
