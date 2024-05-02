package com.sathira.yumcart.module.menu.mapper;

import com.sathira.yumcart.module.menu.dto.MenuItemPortionDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.model.MenuItemPortion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemPortionMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    MenuItemPortionDTO convertMenuItemPortionToResponseDTO(MenuItemPortion menuItemPortion);
}
