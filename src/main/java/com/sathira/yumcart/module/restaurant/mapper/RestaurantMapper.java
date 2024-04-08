package com.sathira.yumcart.module.restaurant.mapper;

import com.sathira.yumcart.module.menu.dto.MenuItemListResponseDTO;
import com.sathira.yumcart.module.menu.mapper.MenuItemMapper;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantListResponseDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = MenuItemMapper.class)
public interface RestaurantMapper {
    RestaurantListResponseDTO convertRestaurentToResponseListDTO(Restaurant restaurant);

    RestaurantResponseDTO convertRestaurentToResponseDTO(Restaurant restaurant);

}
