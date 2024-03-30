    package com.sathira.yumcart.module.restaurant.controller;

    import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
    import com.sathira.yumcart.module.menu.model.MenuItem;
    import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
    import com.sathira.yumcart.module.restaurant.dto.RestaurantListResponseDTO;
    import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
    import com.sathira.yumcart.module.restaurant.model.Restaurant;
    import com.sathira.yumcart.module.restaurant.service.RestaurantService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/restaurant")
    public class RestaurantController {

        private final RestaurantService restaurantService;

        @Autowired
        public RestaurantController(RestaurantService restaurantService) {
            this.restaurantService = restaurantService;
        }

        @GetMapping("/list")
        public ResponseEntity<List<RestaurantListResponseDTO>> getAllRestaurantsMinimalDetailList() {
            List<RestaurantListResponseDTO> restaurants = restaurantService.getRestaurantsList();
            return ResponseEntity.ok(restaurants);
        }

        @GetMapping(value = "/id/{restaurantId}")
        public ResponseEntity<RestaurantResponseDTO> getRestaurant(@PathVariable Long restaurantId) {
            RestaurantResponseDTO restaurants = restaurantService.getRestaurant(restaurantId);
            return ResponseEntity.ok(restaurants);
        }

        @GetMapping("/{restaurantId}/menuItems")
        public ResponseEntity<List<MenuItemResponseDTO>> getRestaurantMenuItems(@PathVariable Long restaurantId) {
            List<MenuItemResponseDTO> menuItems = restaurantService.getRestaurantMenuItems(restaurantId);
            return ResponseEntity.ok(menuItems);
        }

        @PostMapping()
        public ResponseEntity<Restaurant> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
            return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDTO), HttpStatus.CREATED);
        }

    }
