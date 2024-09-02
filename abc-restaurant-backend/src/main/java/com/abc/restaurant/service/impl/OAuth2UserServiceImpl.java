package com.abc.restaurant.service.impl;

import com.abc.restaurant.entity.Admin;
import com.abc.restaurant.entity.Staff;
import com.abc.restaurant.entity.User;
import com.abc.restaurant.enums.CommonStatus;
import com.abc.restaurant.enums.UserStatus;
import com.abc.restaurant.exception.CustomOauthException;
import com.abc.restaurant.repository.AdminRepo;
import com.abc.restaurant.repository.StaffRepo;
import com.abc.restaurant.repository.UserRepo;
import com.abc.restaurant.service.OAuth2UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
@Log4j2
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@RequiredArgsConstructor
public class OAuth2UserServiceImpl implements OAuth2UserService, UserDetailsService {

    private final AdminRepo adminRepo;
    private final UserRepo userRepo;
    private final StaffRepo staffRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<Admin> admin = adminRepo.findByEmail(username);

            if (admin.isPresent() && admin.get().getStatus().equals(CommonStatus.ACTIVE)) {
                return new org.springframework.security.core.userdetails.User(
                        admin.get().getEmail(), admin.get().getPassword(),
                        getAuthority(admin.get().getUserRole().name())
                );
            }

            Optional<User> customer = userRepo.findByEmail(username);

            if (customer.isPresent() && customer.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                return new org.springframework.security.core.userdetails.User(
                        customer.get().getEmail(), customer.get().getPassword(),
                        getAuthority(customer.get().getUserRole().name()));
            }

            Optional<Staff> staff = staffRepo.findByEmail(username);

            if (staff.isPresent() && staff.get().getStatus().equals(CommonStatus.ACTIVE)) {
                return new org.springframework.security.core.userdetails.User(
                        staff.get().getEmail(), staff.get().getPassword(),
                        getAuthority(staff.get().getUserRole().name()));
            }

            throw new CustomOauthException("Invalid Credentials.");

        } catch (Exception e) {
            throw e;
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(String roleName) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName));
    }
}
