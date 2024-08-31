package com.abc.restaurant.entity;


import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "temp_password", nullable = false)
    private String tempPassword;

    @Column(name = "nic")
    private String nic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "created_by")
    private Long createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    private CommonStatus status;

    @Column(name = "user_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updatedDate;

    public Staff(String name, String employeeId, String email, String password, String nic, String phoneNumber,
                 String homeAddress, Long createdBy, Restaurant restaurant, CommonStatus status,
                 UserRole userRole, Date createdDate, Date updatedDate) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.password = password;
        this.nic = nic;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.createdBy = createdBy;
        this.restaurant = restaurant;
        this.status = status;
        this.userRole = userRole;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
