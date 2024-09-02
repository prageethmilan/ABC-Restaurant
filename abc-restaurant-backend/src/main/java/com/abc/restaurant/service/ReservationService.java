package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.MenuItemOrderRequestDTO;
import com.abc.restaurant.dto.request.TableReservationRequestDTO;
import com.abc.restaurant.dto.response.TableReservationResponseDTO;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.exception.ApplicationException;

public interface ReservationService {
    TableReservationResponseDTO saveTableReservation(TableReservationRequestDTO reqDTO) throws ApplicationException;

    void saveMenuItemOrder(MenuItemOrderRequestDTO menuItemOrderRequestDTO);

    Object getReservationsByType(QueryType type, Long id);

    Object getReservationsByTypeAndId(QueryType type, Long id);

    Object getAllReservationsByType(QueryType type);
}
