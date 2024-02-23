package com.sathira.yumcart.module.menu.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_item_portions")
public class MenuItemPortion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Half", "Full"

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

}
