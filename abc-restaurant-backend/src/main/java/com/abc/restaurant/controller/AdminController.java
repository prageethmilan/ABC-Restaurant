package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.SaveAdminRequestDTO;
import com.abc.restaurant.exception.ApplicationException;
import com.abc.restaurant.service.AdminService;
import com.abc.restaurant.util.common.CommonResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> registerAdmin(@ModelAttribute SaveAdminRequestDTO saveAdminRequestDTO) {
        try {
            adminService.saveAdmin(saveAdminRequestDTO);
            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("Admin registered successfully!")
                            .data(null)
                            .build(),
                    HttpStatus.OK
            );
        } catch (Exception e){
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllAdminPortalUsers(){
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .message("")
                        .data(adminService.getAllAdminPortalUsers())
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> findAdminPortalUserByEmail(@RequestParam("email") String email) {
        try {

            return new ResponseEntity<>(
                    CommonResponseUtil.builder()
                            .success(true)
                            .message("")
                            .data(adminService.findAdminPortalUserByEmail(email))
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
}
