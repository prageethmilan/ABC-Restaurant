package com.abc.restaurant.repository;


import com.abc.restaurant.entity.EmailPasswordResetOTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmailPasswordResetOTPRepo extends JpaRepository<EmailPasswordResetOTP, Long> {

    List<EmailPasswordResetOTP> findEmailPasswordResetOTPEntitiesByEmail(String email);

    Optional<EmailPasswordResetOTP> findEmailPasswordResetOTPEntitiesByEmailAndOtp(String email, String otp);
}
