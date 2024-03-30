package com.sathira.yumcart.module.restaurant.service;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.mapper.MenuItemMapper;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantListResponseDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.mapper.RestaurantMapper;
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

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuitemRepository, RestaurantMapper restaurantMapper, MenuItemMapper menuItemMapper) {
        this.restaurantRepository = restaurantRepository;
        this.menuitemRepository = menuitemRepository;
        this.restaurantMapper = restaurantMapper;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    @Transactional
    public List<RestaurantListResponseDTO> getRestaurantsList() {
        List<Restaurant> restaurantList =  restaurantRepository.findAll();
        return restaurantList.stream()
                .map(restaurantMapper::convertRestaurentToResponseListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDTO> getRestaurantMenuItems(Long id) {
        List<MenuItem> menuItemList = menuitemRepository.findByRestaurantId(id);
        return menuItemList.stream()
                .map(menuItemMapper::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponseDTO getRestaurant(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found with id: " + id));
        return restaurantMapper.convertRestaurentToResponseDTO(restaurant);
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

    @Override
    public List<RestaurantResponseDTO> getRestaurants() {
        return null;
    }
}
