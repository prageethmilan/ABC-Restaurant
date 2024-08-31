package com.abc.restaurant.util;

import com.abc.restaurant.exception.ApplicationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

import static com.abc.restaurant.constant.Constants.COMMON_ERROR_CODE;

@Component
@Log4j2
public class OTPGenerator {

    private final Random random = new Random();

    public int generateOTP() {
        try {
            return random.nextInt(9000) + 1000;
        } catch (Exception e) {
            throw new ApplicationException(COMMON_ERROR_CODE, false, "OTP generate process failed");
        }
    }
}
