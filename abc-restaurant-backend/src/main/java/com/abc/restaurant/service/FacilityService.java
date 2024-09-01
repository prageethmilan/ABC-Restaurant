package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveFacilityReqDTO;
import com.abc.restaurant.dto.response.FacilityCommonResponseDTO;

import java.util.List;

public interface FacilityService {
    List<FacilityCommonResponseDTO> getAllFacilities();

    Object getFacilityById(Long id);

    void saveFacility(SaveFacilityReqDTO saveFacilityReqDTO);
}
