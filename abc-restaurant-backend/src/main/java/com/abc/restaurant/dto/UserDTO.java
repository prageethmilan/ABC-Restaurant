package com.abc.restaurant.dto;

import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;

import java.util.Date;

public class UserDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private UserRole userRole;
    private UserStatus userStatus;
    private Date createdDate;
}
