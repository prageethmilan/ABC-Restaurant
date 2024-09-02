package com.abc.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponseDTO<T,S> {
    private T reservation;
    private List<S> items;
    private List<QueryResponseDTO> queries;
}
