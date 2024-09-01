package com.abc.restaurant.controller;

import com.abc.restaurant.dto.request.SaveAdminRequestDTO;
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
        adminService.saveAdmin(saveAdminRequestDTO);
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("Admin registered successfully!")
                        .body(null)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> getAllAdminPortalUsers(){
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(adminService.getAllAdminPortalUsers())
                        .build(),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponseUtil> findAdminPortalUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(
                CommonResponseUtil.builder()
                        .success(true)
                        .msg("")
                        .body(adminService.findAdminPortalUserByEmail(email))
                        .build(),
                HttpStatus.OK
        );
    }


}
