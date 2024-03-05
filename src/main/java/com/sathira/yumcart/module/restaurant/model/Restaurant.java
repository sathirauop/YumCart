package com.sathira.yumcart.module.restaurant.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sathira.yumcart.module.menu.model.MenuItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@RequiredArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    // Assuming each restaurant has a unique menu
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)


    @JsonManagedReference
    private List<MenuItem> menuItems;

//     If implementing reviews
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Review> reviews;

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
        menuItem.setRestaurant(this);
    }

    public void removeMenuItem(MenuItem menuItem) {
        menuItems.remove(menuItem);
        menuItem.setRestaurant(null);
    }



}
