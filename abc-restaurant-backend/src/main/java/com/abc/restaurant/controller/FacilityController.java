package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.SaveFacilityReqDTO;
import com.abc.restaurant.enums.CommonFunctionalFrequency;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.FacilityType;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.service.FacilityService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/facility")
@RequiredArgsConstructor
public class FacilityController {

    @Autowired
    FacilityService facilityService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllFacilities() {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(facilityService.getAllFacilities())
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getFacilityById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("")
                            .data(facilityService.getFacilityById(id))
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (ApplicationException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> saveFacility(
            @RequestParam("id") Long id,
            @RequestParam("restaurantId") Long restaurantId,
            @RequestParam("name") String name,
            @RequestParam(value = "imgUrl", required = false) MultipartFile img,
            @RequestParam("description") String description,
            @RequestParam("frequency")CommonFunctionalFrequency frequency,
            @RequestParam("reservedDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date reservedDate,
            @RequestParam("start") String start,
            @RequestParam("close") String close,
            @RequestParam("weekDays") String weekDays,
            @RequestParam("maxParticipantCount") Integer maxParticipantCount,
            @RequestParam("price") Float price,
            @RequestParam("discount") Float discount,
            @RequestParam("facilityType") FacilityType facilityType,
            @RequestParam("availability") CommonStatus availability
    ) throws IOException {
        try{
            System.out.println(img);
                String projectPath = String.valueOf(new File("E:\\Github Projects\\ABC-Restaurant\\abc-restaurant-frontend\\savedImages"));
                File uploadsDir = new File(projectPath + "\\Facility");
                uploadsDir.mkdir();
                img.transferTo(new File(uploadsDir.getAbsolutePath() + "\\" + img.getOriginalFilename()));
                String imgURL = projectPath + "\\Facility\\" + img.getOriginalFilename();

            SaveFacilityReqDTO saveFacilityReqDTO = SaveFacilityReqDTO.builder()
                    .id(id)
                    .name(name)
                    .restaurantId(restaurantId)
                    .imgURL(imgURL)
                    .description(description)
                    .frequency(frequency)
                    .reservedDate(reservedDate)
                    .start(start)
                    .close(close)
                    .weekDays(weekDays)
                    .maxParticipantCount(maxParticipantCount)
                    .price(price)
                    .discount(discount)
                    .facilityType(facilityType)
                    .availability(availability)
                    .build();

            facilityService.saveFacility(saveFacilityReqDTO);

            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("Facility saved successfully")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .message("An unexpected error occurred.")
                            .data(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (ApplicationException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .message(e.getMessage())
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }
}
