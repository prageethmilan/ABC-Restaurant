package com.abc.restaurant.entity;

import com.abc.restaurant.enums.CommonFunctionalFrequency;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.FacilityType;
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
@Table(name = "facility")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imgURL;

    @Column(length = 1000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "frequency", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'MONTHLY'")
    private CommonFunctionalFrequency frequency;

    @Column(name = "reserved_date")
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date reservedDate;

    private String start;

    private String close;

    @Column(name = "week_days")
    private String weekDays;

    @Column(name = "max_participant_count")
    private Integer maxParticipantCount;

    private Float price;

    private Float discount;

    @Column(name = "facility_type")
    @Enumerated(value = EnumType.STRING)
    private FacilityType facilityType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "availability")
    @Enumerated(value = EnumType.STRING)
    private CommonStatus availability;

    @Column(name = "created_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date createdDate;

    @Column(name = "updated_date")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private Date updatedDate;

    @Override
    public String toString() {
        return "Facility{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", description='" + description + '\'' +
                ", frequency=" + frequency +
                ", reservedDate=" + reservedDate +
                ", start='" + start + '\'' +
                ", close='" + close + '\'' +
                ", weekDays='" + weekDays + '\'' +
                ", maxParticipantCount=" + maxParticipantCount +
                ", price=" + price +
                ", discount=" + discount +
                ", facilityType=" + facilityType +
                ", restaurant=" + restaurant +
                ", createdBy=" + createdBy +
                ", availability=" + availability +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
