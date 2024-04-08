package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.mapper.CategoryMapper;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import com.sathira.yumcart.module.restaurant.service.RestaurantServiceImpl;
import com.sathira.yumcart.utils.exceptions.DuplicateEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

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
        CategoryResposeDTO  categoryDTO = categoryMapper.convertCategoryToResponseDTO(unwrapCategory(category));
        return categoryDTO;
    }

    @Override
    public Category saveCategory(Category category) {
        try{
            return categoryRepository.save(category);
        }catch (DataIntegrityViolationException ex){
            throw new DuplicateEntityException("Category");
        }
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            logger.error("Attempted to delete a category that does not exist with id: {}", id);
            throw new EntityNotFoundException("Restaurant not found with id: " + id);
        }
        categoryRepository.deleteById(id);
        logger.info("Restaurant with id {} successfully deleted", id);
    }

    @Override
    public CategoryResposeDTO findCategoryByName(String name) {
        Optional<Category> category =  categoryRepository.findByName(name);
        CategoryResposeDTO  categoryDTO = categoryMapper.convertCategoryToResponseDTO(unwrapCategory(category));
        return categoryDTO;
    }

    static Category unwrapCategory(Optional<Category> entity) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();
    }


}
