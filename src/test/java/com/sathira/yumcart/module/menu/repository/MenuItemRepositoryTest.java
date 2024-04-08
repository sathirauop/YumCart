package com.sathira.yumcart.module.menu.repository;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class MenuItemRepositoryTest {

    @Autowired
    private CategoryRepository catUnderTest;

    @Autowired
    private RestaurantRepository restUnderTest;

    @Autowired
    private MenuItemRepository underTest;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("skipDataSeeding", "true");
    }
    @AfterAll
    static void afterAll() {
        System.clearProperty("skipDataSeeding");

    }
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
        catUnderTest.deleteAll();
        restUnderTest.deleteAll();
    }

    @Test
    void findByRestaurantId() {

//      given
        Restaurant restaurant1 = new Restaurant(1L,"rest1", "address1", "0717568407",null,new ArrayList<MenuItem>(),null,new ArrayList<Category>());
        restUnderTest.save(restaurant1);
        Long generatedId = restaurant1.getId();

        MenuItem menuItem11 = new MenuItem(1L,"specialrice11","description11",new BigDecimal(1001),null,null,null,null,restaurant1);
        underTest.save(menuItem11);

        //when
        List<MenuItem> expected  =new ArrayList<MenuItem>();
        expected.add(menuItem11);

        List<MenuItem> result = underTest.findByRestaurantId(generatedId);

        //then
        assertEquals(result.size(),expected.size());
        for(int i = 0; i<expected.size();i++){
            assertThat(result.get(i).getId()).isSameAs(expected.get(i).getId());
        }
    }

    @Test
    void findByCategoryId() {

        //when
        Category cat1 = new Category(1L,"category1","Category description1",new ArrayList<MenuItem>(),new ArrayList<Restaurant>());
        catUnderTest.save(cat1);
        Long generatedId = cat1.getId();

        Restaurant restaurant1 = new Restaurant(1L,"rest1", "address1", "0717568407",null,new ArrayList<MenuItem>(),null,new ArrayList<Category>());
        restaurant1.addCategory(cat1);
        restUnderTest.save(restaurant1);

        MenuItem menuItem11 = new MenuItem(1L,"specialrice11","description11",new BigDecimal(1001),null,null,null,null,restaurant1);
        menuItem11.setCategory(cat1);
        underTest.save(menuItem11);


        //when
        List<MenuItem> expected  =new ArrayList<MenuItem>();
        expected.add(menuItem11);

        List<MenuItem> result = underTest.findByCategoryId(generatedId);

        //then
        assertEquals(result.size(),expected.size());
        for(int i = 0; i<expected.size();i++){
            assertThat(result.get(i).getId()).isSameAs(expected.get(i).getId());
        }
    }
}