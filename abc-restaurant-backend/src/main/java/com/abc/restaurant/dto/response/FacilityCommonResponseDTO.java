package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonFunctionalFrequency;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.FacilityType;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class FacilityCommonResponseDTO {
    private Long id;
    private Long restaurantId;
    private String restaurantName;
    private String name;
    private String imgURL;
    private String description;
    private CommonFunctionalFrequency frequency;
    private Date reservedDate;
    private String start;
    private String close;
    private String weekDays;
    private Integer maxParticipantCount;
    private Float price;
    private Float discount;
    private FacilityType facilityType;
    private CommonStatus availability;
    private Date createdDate;
    private Date updatedDate;

    public FacilityCommonResponseDTO(Long id, Long restaurantId, String name, String imgURL, String description, CommonFunctionalFrequency frequency, Date reservedDate, String start, String close, String weekDays, Integer maxParticipantCount, Float price, Float discount, FacilityType facilityType, CommonStatus availability, Date createdDate, Date updatedDate) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.name = name;
        this.imgURL = imgURL;
        this.description = description;
        this.frequency = frequency;
        this.reservedDate = reservedDate;
        this.start = start;
        this.close = close;
        this.weekDays = weekDays;
        this.maxParticipantCount = maxParticipantCount;
        this.price = price;
        this.discount = discount;
        this.facilityType = facilityType;
        this.availability = availability;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
