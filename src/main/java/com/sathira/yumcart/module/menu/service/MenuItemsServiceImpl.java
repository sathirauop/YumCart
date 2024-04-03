package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.CategoryResposeDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.mapper.MenuItemMapper;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MenuItemsServiceImpl implements MenuItemService{

    MenuItemRepository menuItemRepository;
    CategoryRepository categoryRepository;
    CategoryService categoryService;
    RestaurantService restaurantService;
    MenuItemMapper mapper;

    @Autowired
    public MenuItemsServiceImpl(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository, CategoryService categoryService, RestaurantService restaurantService, MenuItemMapper mapper ){
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.restaurantService = restaurantService;
        this.mapper = mapper;
    }

    @Override
    public MenuItemResponseDTO getMenuItem(Long id) {
        Optional<MenuItem> menuItemOptional =  menuItemRepository.findById(id);
        return mapper.convertMenuItemToResponseDTO(unwrapMenuItem(menuItemOptional));
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public List<MenuItemResponseDTO> getMenuItemsbyRestaurent(Long id) {
        List<MenuItem> menuItemList = menuItemRepository.findByRestaurantId(id);
        return menuItemList.stream()
                .map(mapper::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDTO> getMenuItemsbyCategory(Long id) {
        List<MenuItem> menuItemList = menuItemRepository.findByCategoryId(id);
        return menuItemList.stream()
                .map(mapper::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItemResponseDTO> getMenuItemsbyCategoryName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        List<MenuItemResponseDTO> menuItems = this.getMenuItemsbyCategory(((CategoryServiceImpl)categoryService).unwrapCategory(category).getId());
        return menuItems;
    }

    @Override
    public MenuItemResponseDTO createMenuItem(MenuItemDTO menuItemDTO) {
        String restaurentName = menuItemDTO.getRestaurant();
        String categoryName = menuItemDTO.getCategory();
        CategoryResposeDTO category = categoryService.findCategoryByName(categoryName) ;
        RestaurantResponseDTO restaurant = restaurantService.getRestaurant(1L);
        return null;
    }
    static MenuItem unwrapMenuItem(Optional<MenuItem> entity) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();
    }
}
