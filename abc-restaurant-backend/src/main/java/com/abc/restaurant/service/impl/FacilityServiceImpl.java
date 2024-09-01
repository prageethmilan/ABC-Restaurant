package com.abc.restaurant.service.impl;

import com.abc.restaurant.dto.request.SaveFacilityReqDTO;
import com.abc.restaurant.dto.response.FacilityCommonResponseDTO;
import com.abc.restaurant.entity.Facility;
import com.abc.restaurant.entity.Restaurant;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.repository.FacilityRepo;
import com.abc.restaurant.repository.RestaurantRepo;
import com.abc.restaurant.service.FacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    FacilityRepo facilityRepo;

    @Autowired
    RestaurantRepo restaurantRepo;

    @Override
    public List<FacilityCommonResponseDTO> getAllFacilities() {
        return modelMapper.map(facilityRepo.findAll(), new TypeToken<List<FacilityCommonResponseDTO>>(){}.getType());
    }

    @Override
    public Object getFacilityById(Long id) {
        try{
            Optional<Facility> facility = facilityRepo.findById(id);

            if (facility.isPresent()) {
                return mapFacilityCommonResponseDTO(facility.get());
            }

            throw new ApplicationException(200, false, "Facility not found!");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void saveFacility(SaveFacilityReqDTO saveFacilityReqDTO) {
        try{
            Optional<Restaurant> restaurant = restaurantRepo.findById(saveFacilityReqDTO.getRestaurantId());
            if (!restaurant.isPresent() || restaurant.get().getStatus().equals(CommonStatus.DELETED)){
                throw new ApplicationException(200, false, "Restaurant not found");
            }

            if (saveFacilityReqDTO.getId() == 0) {
                Facility newFacility = Facility.builder()
                        .name(saveFacilityReqDTO.getName())
                        .imgURL(saveFacilityReqDTO.getImgURL())
                        .description(saveFacilityReqDTO.getDescription())
                        .frequency(saveFacilityReqDTO.getFrequency())
                        .reservedDate(saveFacilityReqDTO.getReservedDate())
                        .start(saveFacilityReqDTO.getStart())
                        .close(saveFacilityReqDTO.getClose())
                        .weekDays(saveFacilityReqDTO.getWeekDays())
                        .maxParticipantCount(saveFacilityReqDTO.getMaxParticipantCount())
                        .price(saveFacilityReqDTO.getPrice())
                        .discount(saveFacilityReqDTO.getDiscount())
                        .facilityType(saveFacilityReqDTO.getFacilityType())
                        .restaurant(restaurant.get())
                        .availability(saveFacilityReqDTO.getAvailability())
                        .build();

                facilityRepo.save(newFacility);
            } else {
                Optional<Facility> facility = facilityRepo.findById(saveFacilityReqDTO.getId());

                if (!facility.isPresent()){
                    throw new ApplicationException(200, false, "Sorry required facility  not found");
                }

                Facility existingFacility = facility.get();

                String fileUrl = null;
                if (saveFacilityReqDTO.getImgURL() != null) {
                    fileUrl = saveFacilityReqDTO.getImgURL();
                } else {
                    fileUrl = existingFacility.getImgURL();
                }

                existingFacility.setName(saveFacilityReqDTO.getName());
                existingFacility.setImgURL(fileUrl);
                existingFacility.setDescription(saveFacilityReqDTO.getDescription());
                existingFacility.setFrequency(saveFacilityReqDTO.getFrequency());
                existingFacility.setReservedDate(saveFacilityReqDTO.getReservedDate());
                existingFacility.setStart(saveFacilityReqDTO.getStart());
                existingFacility.setClose(saveFacilityReqDTO.getClose());
                existingFacility.setWeekDays(saveFacilityReqDTO.getWeekDays());
                existingFacility.setMaxParticipantCount(saveFacilityReqDTO.getMaxParticipantCount());
                existingFacility.setPrice(saveFacilityReqDTO.getPrice());
                existingFacility.setDiscount(saveFacilityReqDTO.getDiscount());
                existingFacility.setFacilityType(saveFacilityReqDTO.getFacilityType());
                existingFacility.setRestaurant(restaurant.get());
                existingFacility.setAvailability(saveFacilityReqDTO.getAvailability());
                existingFacility.setUpdatedDate(new Date());

                facilityRepo.save(existingFacility);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private FacilityCommonResponseDTO mapFacilityCommonResponseDTO(Facility facility) {
        return FacilityCommonResponseDTO.builder()
                .id(facility.getId())
                .restaurantId(facility.getRestaurant().getId())
                .name(facility.getName())
                .imgURL(facility.getImgURL())
                .description(facility.getDescription())
                .frequency(facility.getFrequency())
                .reservedDate(facility.getReservedDate())
                .start(facility.getStart())
                .close(facility.getClose())
                .weekDays(facility.getWeekDays())
                .maxParticipantCount(facility.getMaxParticipantCount())
                .price(facility.getPrice())
                .discount(facility.getDiscount())
                .facilityType(facility.getFacilityType())
                .availability(facility.getAvailability())
                .createdDate(facility.getCreatedDate())
                .updatedDate(facility.getUpdatedDate())
                .build();
    }
}
