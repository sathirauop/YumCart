package com.sathira.yumcart.module.restaurant.dto;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<MenuItemResponseDTO> menuItems; // Assuming you have a MenuItemDTO
}
