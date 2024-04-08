package com.sathira.yumcart.module.menu.controller;

import com.sathira.yumcart.module.menu.dto.MenuItemDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.service.MenuItemService;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/id/{menuItemId}")
    public ResponseEntity<MenuItemResponseDTO> getMenuItems(@PathVariable Long menuItemId){
        MenuItemResponseDTO menuItems = menuItemService.getMenuItem(menuItemId);
        return ResponseEntity.ok(menuItems);
    }
//////////////////////////////////////////////////////////////////////
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
//    @PostMapping()
//    public ResponseEntity<Restaurant> saveMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
//        return new ResponseEntity<>(menuItemService.createMenuItem(menuItemDTO), HttpStatus.CREATED);
//    }



}