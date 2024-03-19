package com.sathira.yumcart.module.menu.controller;

import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
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
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category>  saveCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/id/{categoryId}")
    public ResponseEntity<Category> getCategorybyId(@PathVariable Long categoryId){
        Optional<Category> category = categoryService.findCategoryById(categoryId);
        return category.map(ResponseEntity::ok) // if category is present, wrap it in a ResponseEntity with OK status
                .orElseGet(() -> ResponseEntity.notFound().build()); // if not present, return 404 Not Found
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<Category> getCategorybyName(@PathVariable String categoryName){
        Optional<Category> category = categoryService.findCategoryByName(categoryName);
        return category.map(ResponseEntity::ok) // if category is present, wrap it in a ResponseEntity with OK status
                .orElseGet(() -> ResponseEntity.notFound().build()); // if not present, return 404 Not Found
    }

}
