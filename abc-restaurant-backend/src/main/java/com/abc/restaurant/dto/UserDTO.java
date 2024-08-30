package com.abc.restaurant.dto;

import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private UserRole userRole;
    private UserStatus userStatus;
    private Date createdDate;
}
