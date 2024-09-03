package com.abc.restaurant.config;
import com.abc.restaurant.dto.UserDTO;
import com.abc.restaurant.entity.Admin;
import com.abc.restaurant.entity.Staff;
import com.abc.restaurant.repository.AdminRepo;
import com.abc.restaurant.repository.StaffRepo;
import com.abc.restaurant.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    private static final Logger log = LoggerFactory.getLogger(CustomTokenEnhancer.class);
    private final AdminRepo adminRepo;
    private final StaffRepo staffRepo;
    private final UserRepo userRepo;


    @Autowired
    public CustomTokenEnhancer(AdminRepo adminRepo, StaffRepo staffRepo, UserRepo userRepo) {
        this.adminRepo = adminRepo;
        this.staffRepo = staffRepo;
        this.userRepo = userRepo;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        System.out.println("enhance method");
        final Map<String, Object> additionalInfo = new HashMap<>();

        User user = (User) oAuth2Authentication.getPrincipal();

        System.out.println("user : "+ user.toString());

        Optional<Admin> admin = adminRepo.findByEmail(user.getUsername());

        if (admin.isPresent()) {
            UserDTO build = UserDTO.builder()
                    .id(admin.get().getId())
                    .email(admin.get().getEmail())
                    .name(admin.get().getName())
                    .nic(admin.get().getNic())
                    .phoneNumber(admin.get().getPhoneNumber())
                    .homeAddress(admin.get().getHomeAddress())
                    .userRole(admin.get().getUserRole())
                    .createdDate(admin.get().getCreatedDate())
                    .updatedDate(admin.get().getUpdatedDate())
                    .build();
            additionalInfo.put("user", build);
        }

        Optional<com.abc.restaurant.entity.User> customer = userRepo.findByEmail(user.getUsername());

        if (customer.isPresent()) {
            UserDTO build = UserDTO.builder()
                    .id(customer.get().getId())
                    .email(customer.get().getEmail())
                    .name(customer.get().getName())
                    .userRole(customer.get().getUserRole())
                    .nic(customer.get().getNic())
                    .homeAddress(customer.get().getHomeAddress())
                    .phoneNumber(customer.get().getPhoneNumber())
                    .createdDate(customer.get().getCreatedDate())
                    .userStatus(customer.get().getUserStatus())
                    .updatedDate(customer.get().getUpdatedDate())
                    .build();
            additionalInfo.put("user", build);
        }

        Optional<Staff> staff = staffRepo.findByEmail(user.getUsername());

        if (staff.isPresent()) {
            UserDTO build = UserDTO.builder()
                    .id(staff.get().getId())
                    .email(staff.get().getEmail())
                    .name(staff.get().getName())
                    .nic(staff.get().getNic())
                    .phoneNumber(staff.get().getPhoneNumber())
                    .homeAddress(staff.get().getHomeAddress())
                    .userRole(staff.get().getUserRole())
                    .createdDate(staff.get().getCreatedDate())
                    .updatedDate(staff.get().getUpdatedDate())
                    .build();
            additionalInfo.put("user", build);
        }

        // set custom claims
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return super.enhance(oAuth2AccessToken, oAuth2Authentication);
    }
}
