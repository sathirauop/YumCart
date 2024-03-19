package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.Category;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.CategoryRepository;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MenuItemsServiceImpl implements MenuItemService{

    MenuItemRepository menuItemRepository;
    CategoryRepository categoryRepository;

    public MenuItemsServiceImpl(MenuItemRepository menuItemRepository, CategoryRepository categoryRepository ){
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public StandAloneMenuItemResponseDTO getMenuItem(Long id) {
        return convertMenuItemToStandAloneResponseDTO(menuItemRepository.getReferenceById(id));
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
                .map(this::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDTO> getMenuItemsbyCategory(Long id) {
        List<MenuItem> menuItemList = menuItemRepository.findByCategoryId(id);
        return menuItemList.stream()
                .map(this::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public List<MenuItemResponseDTO> getMenuItemsbyCategoryName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        List<MenuItemResponseDTO> menuItems = this.getMenuItemsbyCategory(category.orElseThrow().getId());
        return menuItems;
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

    private StandAloneMenuItemResponseDTO convertMenuItemToStandAloneResponseDTO(MenuItem menuItem) {
        return new StandAloneMenuItemResponseDTO(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getImage(),
                menuItem.getCategory()
        );
    }
}
