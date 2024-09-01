package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.SaveMenuItemRequestDTO;
import com.abc.restaurant.dto.response.MenuItemFilterResponseDTO;
import com.abc.restaurant.service.MenuService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/meal")
@RequiredArgsConstructor
public class MenuController {

    @Autowired
    private MenuService menuService;


    @GetMapping
    public ResponseEntity<CommonResponseUtil> getAllMenus(){
        MenuItemFilterResponseDTO responseDTO = MenuItemFilterResponseDTO.builder()
                .restaurantId(0L)
                .meals(menuService.getAllMenus())
                .build();

        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(responseDTO)
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> saveMenuItem(@ModelAttribute SaveMenuItemRequestDTO saveMenuItemRequestDTO) throws IOException {
        menuService.saveMenuItem(saveMenuItemRequestDTO);
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("Menu item saved successfully")
                        .body(null)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getMenuItemById(@PathVariable Long id){
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(menuService.getMenuItemById(id))
                        .build(),
                HttpStatus.OK
        );
    }
}
