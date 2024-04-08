package com.sathira.yumcart.module.menu.mapper;

import com.sathira.yumcart.module.menu.dto.MenuItemListResponseDTO;
import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.dto.StandAloneMenuItemResponseDTO;
import com.sathira.yumcart.module.menu.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface MenuItemMapper {
//    convertMenuItemToResponseDTO
    @Mapping(target = "category", expression = "java(menuItem.getCategory() != null ? menuItem.getCategory().getName() : null)")
    @Mapping(target = "restaurant", expression = "java(menuItem.getRestaurant() != null ? menuItem.getRestaurant().getName() : null)")
    MenuItemResponseDTO convertMenuItemToResponseDTO(MenuItem menuItem);

    @Mapping(target = "category", expression = "java(menuItem.getCategory() != null ? menuItem.getCategory().getName() : null)")
    MenuItemListResponseDTO convertMenuItemToResponseListDTO(MenuItem menuItem);
    StandAloneMenuItemResponseDTO convertMenuItemToStandAloneResponseDTO(MenuItem menuItem);


}
