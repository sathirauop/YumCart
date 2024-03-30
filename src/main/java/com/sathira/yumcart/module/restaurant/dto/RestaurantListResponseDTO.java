package com.sathira.yumcart.module.restaurant.dto;

//import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.restaurant.model.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantListResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String image;
}
