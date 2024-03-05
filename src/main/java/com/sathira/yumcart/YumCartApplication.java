package com.sathira.yumcart;

import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.menu.service.MenuItemService;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class YumCartApplication implements CommandLineRunner {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public static void main(String[] args) {
        SpringApplication.run(YumCartApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        Restaurant restaurant1 = new Restaurant(1L,"helaRawla", "dw", "dfsf",new ArrayList<MenuItem>(),null);
        restaurantRepository.save(restaurant1);
        MenuItem menuItem11 = new MenuItem(2L,"specialrice11","description11",new BigDecimal(1001),null,null,null,null,null);
        MenuItem menuItem12 = new MenuItem(2L,"specialrice12","description12",new BigDecimal(1002),null,null,null,null,null);
        MenuItem menuItem13 = new MenuItem(2L,"specialrice13","description13",new BigDecimal(1003),null,null,null,null,null);
        restaurant1.addMenuItem(menuItem11);
        restaurant1.addMenuItem(menuItem12);
//        restaurant1.addMenuItem(menuItem13);



        menuItemRepository.save(menuItem11);
        menuItemRepository.save(menuItem12);
//        menuItemRepository.save(menuItem13);
//        restaurantRepository.save(restaurant1);







    }

}
