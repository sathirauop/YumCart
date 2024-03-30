package com.sathira.yumcart.module.menu.controller;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/Category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResposeDTO>> getAllCategories(){
        List<CategoryResposeDTO> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category>  saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/id/{categoryId}")
    public ResponseEntity<CategoryResposeDTO> getCategorybyId(@PathVariable Long categoryId){
        CategoryResposeDTO category = categoryService.findCategoryById(categoryId);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<CategoryResposeDTO> getCategorybyName(@PathVariable String categoryName){
        CategoryResposeDTO categories = categoryService.findCategoryByName(categoryName);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<CategoryResposeDTO>> getCategorybyRestaurant(@PathVariable Long restaurantId){
        List<CategoryResposeDTO> categories = categoryService.findCategoriesbyRestaurant (restaurantId);
        return ResponseEntity.ok(categories);
    }

}
