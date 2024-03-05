package com.sathira.yumcart.module.restaurant.service;


import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestaurantService {


    public Restaurant getRestaurant(Long id);

    public Restaurant save(Restaurant restaurant);

    public void deleteRestaurant(Long id);

    public List<Restaurant> getRestaurants();
    public List<MenuItem> getRestaurantMenuItems(Long id);

}
