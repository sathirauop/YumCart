package com.sathira.yumcart.module.menu.model;

import com.sathira.yumcart.module.restaurant.model.Restaurant;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menuitem")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1024) // Assuming description can be lengthy
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemPortion> portions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "menu_item_dietary_labels",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "dietary_label_id")
    )
    private Set<DietaryLabel> dietaryLabels = new HashSet<>();

    //TODO : When the other modules build we have to add more attributes like restaurent

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
