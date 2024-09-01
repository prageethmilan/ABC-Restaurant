package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveFacilityReqDTO;
import com.abc.restaurant.dto.response.FacilityCommonResponseDTO;
import com.abc.restaurant.exception.ApplicationException;

import java.util.List;

public interface FacilityService {
    List<FacilityCommonResponseDTO> getAllFacilities();

    Object getFacilityById(Long id) throws ApplicationException;

    void saveFacility(SaveFacilityReqDTO saveFacilityReqDTO) throws ApplicationException;
}
