package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.MenuItemOrderRequestDTO;
import com.abc.restaurant.dto.request.ReservationApproveRequestDTO;
import com.abc.restaurant.dto.request.SaveQueryRequestDTO;
import com.abc.restaurant.dto.request.TableReservationRequestDTO;
import com.abc.restaurant.enums.QueryType;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.service.QueryService;
import com.abc.restaurant.service.ReservationService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private QueryService queryService;

    @PostMapping(value = "/table")
    public ResponseEntity<CommonResponseUtil> saveTableReservation(@RequestBody TableReservationRequestDTO tableReservationRequestDTO) {
        try{
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("")
                            .data(reservationService.saveTableReservation(tableReservationRequestDTO))
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

    @PostMapping(value = "/meal")
    public ResponseEntity<CommonResponseUtil> saveMenuItemOrder(@RequestBody MenuItemOrderRequestDTO menuItemOrderRequestDTO) {
        reservationService.saveMenuItemOrder(menuItemOrderRequestDTO);
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("Save menu items order successfully")
                        .data(null)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{type}/{orderId}")
    public ResponseEntity<CommonResponseUtil> getReservations(@PathVariable QueryType type, @PathVariable Long orderId) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(queryService.getQueries(type, orderId))
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping("/query")
    public ResponseEntity<CommonResponseUtil> saveQuery(@RequestBody SaveQueryRequestDTO reqDTO) {
        queryService.save(reqDTO);
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("Query saved successfully.")
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/query/{type}/{userId}")
    public ResponseEntity<Object> getQueries(@PathVariable QueryType type, @PathVariable Long userId) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(queryService.getQueries(type, userId))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/order/{type}/{userId}")
    public ResponseEntity<Object> getReservationsByType(@PathVariable QueryType type, @PathVariable Long userId) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(reservationService.getReservationsByType(type, userId))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/by-order/{type}/{orderId}")
    public ResponseEntity<Object> getReservationsByTypeAndId(@PathVariable QueryType type, @PathVariable Long orderId) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(reservationService.getReservationsByTypeAndId(type, orderId))
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/{type}")
    public ResponseEntity<CommonResponseUtil> getReservationByType(@PathVariable QueryType type) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(reservationService.getAllReservationsByType(type))
                        .build(),
                HttpStatus.OK
        );
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<CommonResponseUtil> updateReservationStatus(@PathVariable Long orderId, @RequestBody ReservationApproveRequestDTO reservationApproveRequestDTO) {
        try{
            reservationService.updateReservationStatus(orderId,reservationApproveRequestDTO);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("Reservation status updated successfully")
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

    @GetMapping
    public ResponseEntity<CommonResponseUtil> getAllReservations() {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(reservationService.findAllReservations())
                        .build(),
                HttpStatus.OK
        );
    }
}
