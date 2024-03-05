package com.sathira.yumcart.module.restaurant.service;

import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<MenuItem> getRestaurantMenuItems(Long id) {
       return menuitemRepository.findByRestaurantId(id);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.getReferenceById(id);
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        // TODO:  Business logic to validate restaurant details
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }



}
