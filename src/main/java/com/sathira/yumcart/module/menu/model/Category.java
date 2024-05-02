package com.sathira.yumcart.module.menu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Category")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 1024) // Optional: for categories that need a description
    private String description;

    @OneToMany(mappedBy = "category", orphanRemoval = false)
    @Column(name = "menu_itmes")
    private List<MenuItem> menuItems = new ArrayList<>();


    @ManyToMany(mappedBy = "categories")
    @Column(name = "restaurents")
    private List<Restaurant> restaurants = new ArrayList<>();

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

//    public void addMenuItem(MenuItem menuItem) {
//        menuItems.add(menuItem);
//        menuItem.setCategory(this);
//    }
//    public void addRestaurants(Restaurant restaurant){
//        this.restaurants.add(restaurant);
//    }

}
