package com.sathira.yumcart.module.menu.controller;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItemsbyCategoryName(@PathVariable String categoryName){
        List<MenuItemResponseDTO> menuItems = menuItemService.getMenuItemsbyCategoryName(categoryName);
        return ResponseEntity.ok(menuItems);
    }

}