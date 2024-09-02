package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.SaveQueryRequestDTO;
import com.abc.restaurant.dto.response.QueryResponseDTO;
import com.abc.restaurant.enums.QueryType;

import java.util.List;

public interface QueryService {
    List<QueryResponseDTO> getQueries(QueryType type, Long id);

    void save(SaveQueryRequestDTO saveQueryRequestDTO);
}
