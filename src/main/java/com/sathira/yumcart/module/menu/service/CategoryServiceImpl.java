package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryResposeDTO> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(this::convertRestaurentToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    private CategoryResposeDTO convertRestaurentToResponseDTO(Category category) {
        List<MenuItemResponseDTO> menuItemResponseDTOs = category.getMenuItems().stream()
                .map(this::convertMenuItemToResponseDTO) // You need to implement this method
                .collect(Collectors.toList());

        // Similar conversion for reviews if needed

        return new CategoryResposeDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                menuItemResponseDTOs
        );
        // Include reviews in the constructor if you're handling them
    }

    private MenuItemResponseDTO convertMenuItemToResponseDTO(MenuItem menuItem) {
        String restaurantName = menuItem.getRestaurant() != null ? menuItem.getRestaurant().getName() : null;
        String categoryName = menuItem.getCategory() != null ? menuItem.getCategory().getName() : null;
        return new MenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getImage(),
                categoryName,
                restaurantName
        );
    }
}
