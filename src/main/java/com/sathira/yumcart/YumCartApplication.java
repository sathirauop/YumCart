package com.sathira.yumcart;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
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

    @Autowired
    CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(YumCartApplication.class, args);
    }



    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(1L,"category1","description1",new ArrayList<MenuItem>());
        Category cat2 = new Category(2L,"category2","description2",new ArrayList<MenuItem>());
        categoryRepository.save(cat1);
        categoryRepository.save(cat2);

        Restaurant restaurant1 = new Restaurant(1L,"helaRawla", "dw", "dfsf",null,new ArrayList<MenuItem>(),null);
        Restaurant restaurant2 = new Restaurant(1L,"helaRawla2", "dw2", "dfsf2",null,new ArrayList<MenuItem>(),null);
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);


        MenuItem menuItem11 = new MenuItem(1L,"specialrice11","description11",new BigDecimal(1001),null,null,null,null,restaurant1);
        MenuItem menuItem12 = new MenuItem(2L,"specialrice12","description12",new BigDecimal(1002),null,null,null,null,restaurant2);
//        MenuItem menuItem13 = new MenuItem(3L,"specialrice13","description13",new BigDecimal(1003),null,null,null,null,null);
        menuItem11.setCategory(cat1);
        menuItem12.setCategory(cat2);


//        categoryRepository.save(cat1);


//        menuItem11.setCategory(cat1);


        menuItemRepository.save(menuItem11);
        menuItemRepository.save(menuItem12);
//        menuItemRepository.save(menuItem13);
//        restaurantRepository.save(restaurant1);



//        menuItemRepository.save(menuItem11);
//        menuItemRepository.save(menuItem12);

//        restaurant1.addMenuItem(menuItem11);
//        restaurant1.addMenuItem(menuItem12);
//
//        categoryRepository.save(cat1);
//        cat1.addMenuItem(menuItem11);
//        cat1.addMenuItem(menuItem12);
//
//        menuItemRepository.save(menuItem11);
//        menuItemRepository.save(menuItem12);
//        menuItem11.setCategory(cat1);



//        restaurant1.addMenuItem(menuItem13);
//        menuItemRepository.save(menuItem13);
//        restaurantRepository.save(restaurant1);

    }

}
