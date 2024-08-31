package com.abc.restaurant.entity;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.MenuOrderOperationalStatus;
import com.abc.restaurant.enums.MenuOrderType;
import com.abc.restaurant.enums.PaymentStatus;
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
@Table(name = "menu_item_order")
public class MenuItemsOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orderId", unique = true)
    private String orderId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operational_status")
    private MenuOrderOperationalStatus operationalStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    private CommonStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "menu_order_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ONLINE'")
    private MenuOrderType menuOrderType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updatedDate;
}
