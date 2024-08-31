package com.abc.restaurant.util.response;


import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminStaffCommonResponseDTO {

    Long id;
    String name;
    String employeeId;
    String email;
    String nic;
    String phoneNumber;
    String password;
    String tempPassword;
    String homeAddress;
    Long restaurantId;
    CommonStatus status;
    UserRole userRole;
    Date createdDate;
    Date updatedDate;

    public AdminStaffCommonResponseDTO(Long id, String name, String email, String nic, String phoneNumber, String tempPassword, String homeAddress, CommonStatus status, UserRole userRole, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.tempPassword = tempPassword;
        this.homeAddress = homeAddress;
        this.status = status;
        this.userRole = userRole;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public AdminStaffCommonResponseDTO(Long id, String name, String employeeId, String email, String nic, String phoneNumber, String tempPassword, String homeAddress, Long restaurantId, CommonStatus status, UserRole userRole, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.tempPassword = tempPassword;
        this.homeAddress = homeAddress;
        this.restaurantId = restaurantId;
        this.status = status;
        this.userRole = userRole;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public AdminStaffCommonResponseDTO(Long id, String name, String employeeId, String email, String nic, String phoneNumber, String homeAddress, Long restaurantId, CommonStatus status, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.restaurantId = restaurantId;
        this.status = status;
        this.userRole = userRole;
    }

    public AdminStaffCommonResponseDTO(Long id, String name, String email, String nic, String phoneNumber, String homeAddress, CommonStatus status, UserRole userRole, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.status = status;
        this.userRole = userRole;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
