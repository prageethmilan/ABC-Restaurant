package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.SaveRestaurantRequestDTO;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.service.RestaurantService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<CommonResponseUtil> getAllRestaurants(){
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(restaurantService.getAllRestaurants())
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/branches")
    public ResponseEntity<CommonResponseUtil> getAllRestaurantIds(){
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(restaurantService.getAllRestaurantIds())
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> saveRestaurant(@RequestBody SaveRestaurantRequestDTO saveRestaurantRequestDTO){
        try {
            restaurantService.saveRestaurant(saveRestaurantRequestDTO);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("Restaurant saved successfully")
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e){
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (ApplicationException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(value = "/branch/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getRestaurantById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .msg("")
                            .body(restaurantService.getRestaurantById(id))
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(false)
                            .msg("An unexpected error occurred.")
                            .body(null)
                            .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        } catch (ApplicationException e) {
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(e.isSuccess())
                            .msg(e.getMessage())
                            .body(null)
                            .build(),
                    HttpStatus.OK
            );
        }
    }
}
