package com.sathira.yumcart.module.menu.repository;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository underTest;

    @Autowired
    private RestaurantRepository restUnderTest;

    @Autowired
    private MenuItemRepository menuUnderTest;

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
        menuUnderTest.deleteAll();
        underTest.deleteAll();
        restUnderTest.deleteAll();
    }

    @Test
    void findByMenuItemsId() {

        //given
        Category cat1 = new Category(1L,"category1","xxxxxx1",new ArrayList<MenuItem>(),new ArrayList<Restaurant>());
        underTest.save(cat1);

        Restaurant restaurant1 = new Restaurant(1L,"rest1", "xxxxxxx", "dfsf",null,new ArrayList<MenuItem>(),null,new ArrayList<Category>());
        restaurant1.addCategory(cat1);
        restUnderTest.save(restaurant1);

        MenuItem menuItem11 = new MenuItem(1L,"specialrice11","description11",new BigDecimal(1001),null,null,null,null,restaurant1);
        menuItem11.setCategory(cat1);
        menuUnderTest.save(menuItem11);
        Long generatedId = menuItem11.getId();

        //when
        List<Category> expected  =new ArrayList<Category>();
        expected.add(cat1);

        Optional<Category> result = underTest.findByMenuItemsId(generatedId);

        //then
        assertThat(result.get()).isNotNull();
        Category cat = result.get();
        assertThat(cat.getId()).isSameAs(cat1.getId());

    }

    @Test
    void findByRestaurantId() {

        //given
        Category cat1 = new Category(1L,"category1","xxxxxx1",new ArrayList<MenuItem>(),new ArrayList<Restaurant>());
        underTest.save(cat1);

        Restaurant restaurant1 = new Restaurant(1L,"rest1", "xxxxxxx", "dfsf",null,new ArrayList<MenuItem>(),null,new ArrayList<Category>());
        restaurant1.addCategory(cat1);
        restUnderTest.save(restaurant1);
        Long generatedId = restaurant1.getId();

        //when
        List<Category> expected  =new ArrayList<Category>();
        expected.add(cat1);

        List<Category> result = underTest.findByRestaurantId(generatedId);

//      then
        assertEquals(expected.size(),result.size());
        for(int i = 0; i<expected.size();i++){
            assertThat(result.get(i).getId()).isSameAs(expected.get(i).getId());
        }
    }

    @Test
    void findByName() {
        //given
        Category cat1 = new Category(1L,"category1","description",new ArrayList<MenuItem>(),new ArrayList<Restaurant>());
        underTest.save(cat1);

        //when
        Optional<Category> result = underTest.findByName("category1");

        //then
        assertThat(result.get()).isNotNull();
        Category cat = result.get();
        assertThat(cat.getId()).isSameAs(cat1.getId());
    }
}