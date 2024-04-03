package com.sathira.yumcart.module.restaurant.service;

import com.sathira.yumcart.module.menu.dto.MenuItemResponseDTO;
import com.sathira.yumcart.module.menu.mapper.MenuItemMapper;
import com.sathira.yumcart.module.menu.model.MenuItem;
import com.sathira.yumcart.module.menu.repository.MenuItemRepository;
import com.sathira.yumcart.module.restaurant.dto.RestaurantDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantListResponseDTO;
import com.sathira.yumcart.module.restaurant.dto.RestaurantResponseDTO;
import com.sathira.yumcart.module.restaurant.mapper.RestaurantMapper;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import com.sathira.yumcart.module.restaurant.repository.RestaurantRepository;
import com.sathira.yumcart.utils.exceptions.DuplicateEntityException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuitemRepository;

    private final RestaurantMapper restaurantMapper;
    private final MenuItemMapper menuItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuitemRepository, RestaurantMapper restaurantMapper, MenuItemMapper menuItemMapper) {
        this.restaurantRepository = restaurantRepository;
        this.menuitemRepository = menuitemRepository;
        this.restaurantMapper = restaurantMapper;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    @Transactional
    public List<RestaurantListResponseDTO> getRestaurantsList() {
        List<Restaurant> restaurantList =  restaurantRepository.findAll();
        return restaurantList.stream()
                .map(restaurantMapper::convertRestaurentToResponseListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuItemResponseDTO> getRestaurantMenuItems(Long id) {
        if (!restaurantRepository.existsById(id)) {
            logger.error("Failed to find restaurant with id {}", id);
            throw new EntityNotFoundException("Restaurant not found with id: " + id);
        }
        List<MenuItem> menuItemList = menuitemRepository.findByRestaurantId(id);
        return menuItemList.stream()
                .map(menuItemMapper::convertMenuItemToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponseDTO getRestaurant(Long id) {
        try{
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            return restaurantMapper.convertRestaurentToResponseDTO(unwrapRestaurant(restaurant));
        }catch (EntityNotFoundException e){
            logger.error("Failed to find restaurant with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        try{
            Restaurant restaurant = new Restaurant();
            restaurant.setName(restaurantDTO.getName());
            restaurant.setAddress(restaurantDTO.getAddress());
            restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
            return restaurantRepository.save(restaurant);
        }catch(DataIntegrityViolationException ex){
            throw new DuplicateEntityException("Restaurant");
        }

    }

    @Override
    @Transactional
    public void deleteRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            logger.error("Attempted to delete a restaurant that does not exist with id: {}", id);
            throw new EntityNotFoundException("Restaurant not found with id: " + id);
        }
        restaurantRepository.deleteById(id);
        logger.info("Restaurant with id {} successfully deleted", id);
    }

    @Override
    public List<RestaurantResponseDTO> getRestaurants() {
        return null;
    }

    static Restaurant unwrapRestaurant(Optional<Restaurant> entity) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException();
    }
}
