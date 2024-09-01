package com.abc.restaurant.dto.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserResetPasswordRequestDTO {
    private String password;
    private int otp;
    private String email;
}
