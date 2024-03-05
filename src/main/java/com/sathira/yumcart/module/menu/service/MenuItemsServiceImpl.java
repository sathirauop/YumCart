package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemsServiceImpl implements MenuItemService{

    MenuItemRepository menuItemRepository;

    public MenuItemsServiceImpl(MenuItemRepository menuItemRepository ){
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem getMenuItem(Long id) {
        return menuItemRepository.getReferenceById(id);
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
    public List<MenuItem> getMenuItemsbyRestaurent(Long id) {
        return menuItemRepository.findByRestaurantId(id);
    }
}
