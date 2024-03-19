package com.sathira.yumcart.module.menu.dto;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// Currently this class is not using

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StandAloneMenuItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private Category category;

//  private RestaurantResponseDTO restaurant;
}
