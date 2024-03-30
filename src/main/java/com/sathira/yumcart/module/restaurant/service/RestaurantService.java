package com.sathira.yumcart.module.restaurant.service;


import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantListResponseDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {


    public RestaurantResponseDTO getRestaurant(Long id);

    public Restaurant createRestaurant(RestaurantDTO restaurantDto);

    public void deleteRestaurant(Long id);

    public List<RestaurantResponseDTO> getRestaurants();

    public List<RestaurantListResponseDTO> getRestaurantsList();

    public List<MenuItemResponseDTO> getRestaurantMenuItems(Long id);

}
