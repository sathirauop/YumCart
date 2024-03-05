package com.sathira.yumcart.module.menu.controller;

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

@RestController
@RequestMapping("/api/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{restaurantId}/menuItems")
    public ResponseEntity<List<MenuItem>> getMenuItemsbyRestaurantId(@PathVariable Long restaurantId){
        List<MenuItem> menuItems = menuItemService.getMenuItemsbyRestaurent(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    // Define endpoints for CRUD operations on menu items
}