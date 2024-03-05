package com.sathira.yumcart.module.menu.repository;

import com.sathira.yumcart.module.menu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByMenuItemsId(Long menuItemId);
    Optional<Category> findByName(String name);

}
