package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.MenuOrderOperationalStatus;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.enums.TableReservationOperationalStatus;
import com.abc.restaurant.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationApproveRequestDTO {
    private QueryType type;
    private MenuOrderOperationalStatus mStatus;
    private TableReservationOperationalStatus tStatus;
    String note;
    Long userId;
    private UserRole userRole;
}
