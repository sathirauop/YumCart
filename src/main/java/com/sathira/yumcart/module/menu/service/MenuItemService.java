package com.sathira.yumcart.module.menu.service;

import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.model.Restaurant;

import java.util.List;

public interface MenuItemService {

    public MenuItem getMenuItem(Long id);

    public MenuItem save(MenuItem menuItem);

    public void deleteMenuItem(Long id);

    public List<MenuItem> getMenuItemsbyRestaurent(Long id);
}
