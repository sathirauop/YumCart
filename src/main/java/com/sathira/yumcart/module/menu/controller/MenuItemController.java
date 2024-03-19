package com.sathira.yumcart.module.menu.controller;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.service.MenuItemService;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{restaurantId}/restaurant")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItemsbyRestaurantId(@PathVariable Long restaurantId){
        List<MenuItemResponseDTO> menuItems = menuItemService.getMenuItemsbyRestaurent(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    @GetMapping("/{categoryName}/category")
    public ResponseEntity<List<StandAloneMenuItemResponseDTO>> getMenuItemsbyCategoryName(@PathVariable String categoryName){
        List<StandAloneMenuItemResponseDTO> menuItems = menuItemService.getMenuItemsbyCategoryName(categoryName);
        return ResponseEntity.ok(menuItems);
    }

}