package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {

    List<CategoryResposeDTO> findAllCategories();
    Optional<Category> findCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Long id);
    Optional<Category> findCategoryByName(String name);

}
