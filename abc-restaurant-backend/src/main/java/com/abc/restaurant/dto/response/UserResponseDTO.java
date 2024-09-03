package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String nic;
    private String phoneNumber;
    private String homeAddress;
    private UserRole userRole;
    private UserStatus userStatus;
    private Date createdDate;
}
