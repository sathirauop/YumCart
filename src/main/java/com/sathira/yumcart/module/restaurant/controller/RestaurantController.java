package com.sathira.yumcart.module.restaurant.controller;

import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}/menuItems")
    public ResponseEntity<List<MenuItem>> getRestaurantMenuItems(@PathVariable Long restaurantId) {
        List<MenuItem> menuItems = restaurantService.getRestaurantMenuItems(restaurantId);
        return ResponseEntity.ok(menuItems);
    }

    @PostMapping()
    public ResponseEntity<Restaurant> saveRestaurant(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.save(restaurant), HttpStatus.CREATED);
    }


}
