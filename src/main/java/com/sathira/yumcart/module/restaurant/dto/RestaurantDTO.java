package com.sathira.yumcart.module.restaurant.dto;

//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {

    @NotBlank(message = "Restaurant name is required")
    @Size(max = 255, message = "Restaurant name must not exceed 255 characters")
    private String name;

    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;

//    @NotBlank(message = "Phone number is required")
//    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private String phoneNumber;

//    private List<MenuItemDTO> menuItems; // Assuming MenuItemDTO is defined elsewhere

//    private List<ReviewDTO> reviews; // Assuming ReviewDTO is defined elsewhere

}


// TODO : Need to add proper validation into the request DTOs