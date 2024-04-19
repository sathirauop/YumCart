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
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantMapper restaurantMapper;

    @Mock
    private MenuItemMapper menuItemMapper;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    private Restaurant testRestaurant;
    private RestaurantDTO testRestaurantDTO;
    private RestaurantResponseDTO testRestaurantResponseDTO;

    @BeforeEach
    void setUp() {
        // Setup your test data
        testRestaurant = new Restaurant();
        testRestaurant.setId(1L);
        testRestaurant.setName("Test Restaurant");

        testRestaurantDTO = new RestaurantDTO();
        testRestaurantDTO.setName("Test Restaurant DTO");

        testRestaurantResponseDTO = new RestaurantResponseDTO();
        testRestaurantResponseDTO.setName("Test Restaurant ResponseDTO");
    }

    @Test
    void getRestaurantsList_ReturnsNonEmptyList_oneItem() {
        when(restaurantRepository.findAll()).thenReturn(Collections.singletonList(testRestaurant));
        RestaurantListResponseDTO responseDTO =  new RestaurantListResponseDTO();
        responseDTO.setName("Test Restaurant");
        when(restaurantMapper.convertRestaurentToResponseListDTO(any(Restaurant.class)))
                .thenReturn(responseDTO);
        List<RestaurantListResponseDTO> result = restaurantService.getRestaurantsList();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo("Test Restaurant");
    }

    @Test
    void getRestaurantsList_ReturnsNonEmptyList_multipleItmes() {

        Restaurant testRestaurant2 = new Restaurant(); // Assuming a constructor or setters to initialize
        testRestaurant2.setName("Test Restaurant 2");

        when(restaurantRepository.findAll()).thenReturn(Arrays.asList(testRestaurant, testRestaurant2));
        RestaurantListResponseDTO responseDTO =  new RestaurantListResponseDTO();
        responseDTO.setName("Test Restaurant");
        RestaurantListResponseDTO responseDTO2 =  new RestaurantListResponseDTO();
        responseDTO2.setName("Test Restaurant 2");

        // Use an Answer to dynamically return different DTOs based on the input restaurant
        when(restaurantMapper.convertRestaurentToResponseListDTO(any(Restaurant.class)))
                .thenAnswer(invocation -> {
                    Restaurant argumentRestaurant = invocation.getArgument(0);
                    if ("Test Restaurant".equals(argumentRestaurant.getName())) {
                        return responseDTO;
                    } else if ("Test Restaurant 2".equals(argumentRestaurant.getName())) {
                        return responseDTO2;
                    }
                    return null; // Default case, not expected
                });

        List<RestaurantListResponseDTO> result = restaurantService.getRestaurantsList();

        // Assertions
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Test Restaurant");
        assertThat(result.get(1).getName()).isEqualTo("Test Restaurant 2");
    }

    @Test
    void getRestaurantMenuItems_WhenRestaurantExists() {
        Long restaurantId = 1L;
        when(restaurantRepository.existsById(restaurantId)).thenReturn(true);
        MenuItem menuItem = new MenuItem();
        when(menuItemRepository.findByRestaurantId(restaurantId)).thenReturn(Collections.singletonList(menuItem));
        MenuItemResponseDTO menuItemResponseDTO = new MenuItemResponseDTO();
        when(menuItemMapper.convertMenuItemToResponseDTO(menuItem)).thenReturn(menuItemResponseDTO);

        // When
        List<MenuItemResponseDTO> result = restaurantService.getRestaurantMenuItems(restaurantId);

        // Then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(menuItemResponseDTO);
        verify(menuItemRepository, times(1)).findByRestaurantId(restaurantId);
        verify(menuItemMapper, times(1)).convertMenuItemToResponseDTO(menuItem);
    }

    @Test
    void getRestaurantMenuItems_WhenRestaurantDoesNotExist_ThrowsEntityNotFoundException() {
        // Given
        Long restaurantId = 1L;
        when(restaurantRepository.existsById(restaurantId)).thenReturn(false);

        // When + Then
        assertThrows(EntityNotFoundException.class, () -> restaurantService.getRestaurantMenuItems(restaurantId));
        verify(restaurantRepository, times(1)).existsById(restaurantId);
        verify(menuItemRepository, times(0)).findByRestaurantId(anyLong());
    }


//
//    @Test
//    void getRestaurant_WhenRestaurantNotFound_ThrowsException() {
//        when(restaurantRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> restaurantService.getRestaurant(1L))
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessageContaining("Restaurant not found with id: 1");
//    }
//
//    @Test
//    void createRestaurant_WhenSuccessful_ReturnsSavedRestaurant() {
//        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(testRestaurant);
//        when(restaurantMapper.convertRestaurentToResponseDTO(any(Restaurant.class))).thenReturn(testRestaurantResponseDTO);
//
//        RestaurantResponseDTO result = restaurantService.createRestaurant(testRestaurantDTO);
//
//        assertThat(result.getName()).isEqualTo(testRestaurantResponseDTO.getName());
//    }
//
//    @Test
//    void createRestaurant_WhenDuplicateEntry_ThrowsException() {
//        when(restaurantRepository.save(any(Restaurant.class)))
//                .thenThrow(new DataIntegrityViolationException("Duplicate entry"));
//
//        assertThatThrownBy(() -> restaurantService.createRestaurant(testRestaurantDTO))
//                .isInstanceOf(DuplicateEntityException.class)
//                .hasMessageContaining("Duplicate entity");
//    }
//
//    @Test
//    void deleteRestaurant_WhenRestaurantDoesNotExist_ThrowsException() {
//        when(restaurantRepository.existsById(anyLong())).thenReturn(false);
//
//        assertThatThrownBy(() -> restaurantService.deleteRestaurant(1L))
//                .isInstanceOf(EntityNotFoundException.class)
//                .hasMessageContaining("Restaurant not found with id: 1");
//    }
}