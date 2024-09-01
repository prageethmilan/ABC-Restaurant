package com.abc.restaurant.util.common;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class CommonResponseUtil {
    private boolean success;
    private String message;
    private Object data;
}
