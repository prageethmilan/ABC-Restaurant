package com.abc.restaurant.entity;

import com.abc.restaurant.enums.UserRole;
import com.abc.restaurant.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nic")
    String nic;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "home_address")
    String homeAddress;

    @Column(name = "user_role", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    Date updatedDate;

    public User(String name, String email, String password, UserRole userRole, UserStatus userStatus) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
}
