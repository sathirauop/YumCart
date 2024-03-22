package com.sathira.yumcart.module.menu.dto;

import com.sathira.yumcart.module.menu.model.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryResposeDTO {
    private Long id;
    private String name;
    private String description;
    private List<MenuItemResponseDTO> menuItems;
}
