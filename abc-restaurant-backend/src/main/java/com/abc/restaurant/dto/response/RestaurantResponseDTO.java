
package com.abc.restaurant.dto.response;

import com.abc.restaurant.enums.CommonStatus;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String branchCode;
    private CommonStatus status;
    private Date createdDate;
    private Date updatedDate;

}
