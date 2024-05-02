package com.sathira.yumcart;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.model.MenuItemPortion;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import com.sathira.yumcart.module.menu.repository.MenuItemPortionRepository;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.menu.service.MenuItemService;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class YumCartApplication implements CommandLineRunner {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MenuItemPortionRepository menuItemPortionRepository;

    public static void main(String[] args) {
        SpringApplication.run(YumCartApplication.class, args);
    }



    @Override
    @Profile("!test")
    public void run(String... args) throws Exception {

        if ("true".equals(System.getProperty("skipDataSeeding"))) {
            return;
        }

        Category cat1 = new Category("category1","description1");
        Category cat2 = new Category("category2","description2");

        MenuItem menuItem11 = new MenuItem("specialrice11","description11",new BigDecimal(1001),"image1");
        MenuItem menuItem12 = new MenuItem("specialrice12","description12",new BigDecimal(1002),"image2");
        MenuItem menuItem13 = new MenuItem("specialr","description13",new BigDecimal(1002),"image3");
        MenuItem menuItem14 = new MenuItem("specialrdfs","description14",new BigDecimal(1002),"image4");
        MenuItem menuItem15 = new MenuItem("specialricdsfsd","description15",new BigDecimal(1002),"image5");


        Restaurant restaurant1 = new Restaurant("helaRawla", "dw", "0734567876");
        Restaurant restaurant2 = new Restaurant("helaRawla2", "dw2", "0975736272");
        Restaurant restaurant3 = new Restaurant("HelaRaula5", "adashhhdasdsn","07127693328");

        MenuItemPortion portion1 = new MenuItemPortion("MI1Full", new BigDecimal(1042));
        MenuItemPortion portion2 = new MenuItemPortion("MI1Half", new BigDecimal(1042));

        restaurant1.addCategory(cat1);
        restaurant2.addCategory(cat1);
        restaurant3.addCategory(cat1);
        restaurant2.addCategory(cat2);
        restaurant3.addCategory(cat2);

        menuItem11.setCategory(cat1);
        menuItem12.setCategory(cat2);
        menuItem13.setCategory(cat1);
        menuItem14.setCategory(cat2);
        menuItem15.setCategory(cat2);

        menuItem11.addPortion(portion1);
        menuItem11.addPortion(portion2);

        restaurant1.addMenuItem(menuItem11);
        restaurant1.addMenuItem(menuItem12);
        restaurant2.addMenuItem(menuItem13);

        // Save the Entities
        categoryRepository.save(cat1);
        categoryRepository.save(cat2);

        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
        restaurantRepository.save(restaurant3);

        menuItemRepository.save(menuItem11);
        menuItemRepository.save(menuItem12);
        menuItemRepository.save(menuItem13);
        menuItemRepository.save(menuItem14);
        menuItemRepository.save(menuItem15);

        menuItemPortionRepository.save(portion1);
        menuItemPortionRepository.save(portion2);

        /*

        NOTE: If we use CASCADE when we persist or remove or refresh it also affects tp the child entity as well.
              Eg: If we set cascade.PERSISt with restaurent and category when we save the restaurent it tries to save the
                  categories we have set to the restaurent as well.
                  After saveing a category one time which is created using new keyword it de.achs from the persistant context.
                  And if we try to save a new restaurent with the same category agian it tries to save it to the database and
                  gives an error.
                  So no casdeding has been used

        NOTE: Considering Restaurent.menuItems and category in current implementation there can be a restaurent which has
              cat1. But also it can contain a menuItem whose category is cat2. (Maybe we have to handle this when we save daata manually
         */

    }

}
