package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserRole;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class SaveAdminRequestDTO {
    private Long id;
    private String employeeId;
    private String email;
    private String homeAddress;
    private Long restaurantId;
    private String name;
    private String nic;
    private String password;
    private String phoneNumber;
    private UserRole role;
    private CommonStatus status;
}
