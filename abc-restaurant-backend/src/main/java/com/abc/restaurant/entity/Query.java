package com.abc.restaurant.entity;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Table(name = "query")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_item_order_id")
    private MenuItemsOrder menuItemsOrder;

    @ManyToOne
    @JoinColumn(name = "table_reservation_id")
    private TableReservation tableReservation;

    @Column(name = "query_type")
    @Enumerated(value = EnumType.STRING)
    private QueryType queryType;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "user_role")
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

    @Enumerated(value = EnumType.STRING)
    private CommonStatus status;
}
