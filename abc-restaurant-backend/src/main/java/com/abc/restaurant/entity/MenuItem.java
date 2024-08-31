package com.abc.restaurant.entity;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuMainCategory;
import com.abc.restaurant.enums.MenuSubCategory;
import com.abc.restaurant.enums.MenuType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "menu_item")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String image;

    @Column(length = 1000)
    private String description;

    private Float price;

    private Float discount;

    private Long rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'EXTRA'")
    private MenuSubCategory subCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'LUNCH'")
    private MenuMainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "menu_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'SRI_LANKAN'")
    private MenuType menuType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    private CommonStatus status;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updatedDate;
}
