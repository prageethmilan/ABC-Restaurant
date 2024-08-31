package com.abc.restaurant.entity;

import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.TableReservationOperationalStatus;
import com.abc.restaurant.enums.TableReservationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
@Table(name = "table_reservation")
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_code")
    private String reservationCode;

    private Integer maxCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private User user;

    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    @Column(name = "reserved_date")
    private Date reservedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'ACTIVE'")
    private CommonStatus status;

    @Column(name = "approved_by")
    private Long approvedBy;

    @Column(name = "approved_note", length = 1000)
    private String approvedNote;

    @Column(name = "customer_note", length = 1000)
    private String customerNote;

    @Enumerated(EnumType.STRING)
    @Column(name = "table_reservation_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'FAMILY_DINING'")
    private TableReservationType tableReservationType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operational_status", columnDefinition = "VARCHAR(255) DEFAULT 'NEW'")
    private TableReservationOperationalStatus operationalStatus;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updatedDate;
}
