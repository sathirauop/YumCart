package com.sathira.yumcart.module.menu.dto;

import com.sathira.yumcart.module.menu.model.MenuItemPortion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private String category;
    private String restaurant;
    private List<MenuItemPortionDTO> portions;
//  private Set<DietaryLabel> dietaryLabels;
}
