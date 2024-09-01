package com.abc.restaurant.dto.request;

import com.abc.restaurant.enums.CommonFunctionalFrequency;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.FacilityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveFacilityReqDTO {
    private Long id;
    private Long restaurantId;
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
}
