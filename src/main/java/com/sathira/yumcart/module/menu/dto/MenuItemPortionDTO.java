package com.sathira.yumcart.module.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuItemPortionDTO {
    private String name;
    private BigDecimal price;
}
