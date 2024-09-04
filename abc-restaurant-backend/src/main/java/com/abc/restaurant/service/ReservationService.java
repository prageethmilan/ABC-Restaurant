package com.abc.restaurant.service;

import com.abc.restaurant.dto.request.MenuItemOrderRequestDTO;
import com.abc.restaurant.dto.request.ReservationApproveRequestDTO;
import com.abc.restaurant.dto.request.TableReservationRequestDTO;
import com.abc.restaurant.dto.response.ReservationResponseDTO;
import com.abc.restaurant.dto.response.TableReservationResponseDTO;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.exception.ApplicationException;

import java.util.List;

public interface ReservationService {
    TableReservationResponseDTO saveTableReservation(TableReservationRequestDTO reqDTO) throws ApplicationException;

    List<TableReservationResponseDTO> findAllReservations();

    void saveMenuItemOrder(MenuItemOrderRequestDTO menuItemOrderRequestDTO);

    Object getReservationsByType(QueryType type, Long id);

    Object getReservationsByTypeAndId(QueryType type, Long id);

    Object getAllReservationsByType(QueryType type);

    void updateReservationStatus(Long orderId, ReservationApproveRequestDTO requestDTO) throws ApplicationException;
}
