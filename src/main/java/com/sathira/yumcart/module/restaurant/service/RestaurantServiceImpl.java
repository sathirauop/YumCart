package com.sathira.yumcart.module.restaurant.service;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuitemRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuitemRepository ) {
        this.restaurantRepository = restaurantRepository;
        this.menuitemRepository = menuitemRepository;
    }

    @Override
    @Transactional
    public List<RestaurantResponseDTO> getRestaurants() {
        List<Restaurant> restaurantList =  restaurantRepository.findAll();
        return restaurantList.stream()
                .map(this::convertRestaurentToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDTO> getRestaurantMenuItems(Long id) {
        List<MenuItem> menuItemList = menuitemRepository.findByRestaurantId(id);
        return menuItemList.stream()
                .map(this::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponseDTO getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        return convertRestaurentToResponseDTO(restaurant);
    }

    @Override
    @Transactional
    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }


    private RestaurantResponseDTO convertRestaurentToResponseDTO(Restaurant restaurant) {
        List<MenuItemResponseDTO> menuItemResponseDTOs = restaurant.getMenuItems().stream()
                .map(this::convertMenuItemToResponseDTO) // You need to implement this method
                .collect(Collectors.toList());

        // Similar conversion for reviews if needed

        return new RestaurantResponseDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhoneNumber(),
                menuItemResponseDTOs
        );
        // Include reviews in the constructor if you're handling them
    }

    private MenuItemResponseDTO convertMenuItemToResponseDTO(MenuItem menuItem) {
        String categoryName = menuItem.getCategory() != null ? menuItem.getCategory().getName() : null;
        String restaurantName = menuItem.getRestaurant() != null ? menuItem.getRestaurant().getName() : null;
        return new MenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getImage(),
                categoryName,
                restaurantName
        );
    }


}
