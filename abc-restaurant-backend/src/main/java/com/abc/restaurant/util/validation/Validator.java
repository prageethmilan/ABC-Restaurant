package com.abc.restaurant.util.validation;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=`~\\[\\]{}|;:'\",.<>/?]).{8,}$";

    public boolean isValidEmail(String email){return email.matches(EMAIL_REGEX);}
    public boolean isValidPassword(String password){return password.matches(PASSWORD_REGEX);}
}
