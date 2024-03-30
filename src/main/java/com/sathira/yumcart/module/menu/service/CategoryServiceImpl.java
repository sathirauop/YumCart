package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.mapper.CategoryMapper;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryResposeDTO> findAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList.stream()
                .map(categoryMapper::convertCategoryToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResposeDTO> findCategoriesbyRestaurant(Long restaurantId) {
        List<Category> categories =  categoryRepository.findByRestaurantId(restaurantId);
        return categories.stream()
                .map(categoryMapper::convertCategoryToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResposeDTO findCategoryById(Long id) {
        Optional<Category> category =  categoryRepository.findById(id);
        CategoryResposeDTO  categoryDTO = categoryMapper.convertCategoryToResponseDTO(category.get());
//        List<MenuItemResponseDTO> menuItems = this.getMenuItemsbyCategory(category.orElseThrow().getId());
        return categoryDTO;
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
    public CategoryResposeDTO findCategoryByName(String name) {
        Optional<Category> category =  categoryRepository.findByName(name);
        CategoryResposeDTO  categoryDTO = categoryMapper.convertCategoryToResponseDTO(category.get());
//        List<MenuItemResponseDTO> menuItems = this.getMenuItemsbyCategory(category.orElseThrow().getId());
        return categoryDTO;
    }


}
