package com.sathira.yumcart.module.restaurant.dto;

import com.sathira.yumcart.module.menu.dto.MenuItemListResponseDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.restaurant.model.OpeningHours;
import com.sathira.yumcart.module.restaurant.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<MenuItemListResponseDTO> menuItems; // Assuming you have a MenuItemDTO
//  private List<OpeningHours> openingHours;
//  private List<Review> reviews;
}
