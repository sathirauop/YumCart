package com.sathira.yumcart.module.menu.repository;

import com.sathira.yumcart.module.menu.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    // Find all MenuItems by a specific Restaurant ID
    List<MenuItem> findByRestaurantId(Long restaurantId);
}
