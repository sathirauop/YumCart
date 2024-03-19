package com.sathira.yumcart.module.menu.dto;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MenuItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private String category;
    private String restaurant;
//  private List<MenuItemPortion> portions;
//  private Set<DietaryLabel> dietaryLabels;
}
