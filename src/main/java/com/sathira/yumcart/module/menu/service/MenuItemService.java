package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.MenuItemDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface MenuItemService {

    public StandAloneMenuItemResponseDTO getMenuItem(Long id);

    public MenuItem save(MenuItem menuItem);

    public void deleteMenuItem(Long id);

    public List<MenuItemResponseDTO> getMenuItemsbyRestaurent(Long id);

    public List<MenuItemResponseDTO> getMenuItemsbyCategory(Long id);

    public List<MenuItemResponseDTO> getMenuItemsbyCategoryName(String name);

    public MenuItemResponseDTO createMenuItem(MenuItemDTO menuItemDTO);


}
