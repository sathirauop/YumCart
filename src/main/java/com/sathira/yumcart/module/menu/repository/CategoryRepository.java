package com.sathira.yumcart.module.menu.repository;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByMenuItemsId(Long menuItemId);

    @Query("SELECT c FROM Category c JOIN c.restaurants r WHERE r.id = :restaurantId")
    List<Category> findByRestaurantId(Long restaurantId);
    Optional<Category> findByName(String name);


}
