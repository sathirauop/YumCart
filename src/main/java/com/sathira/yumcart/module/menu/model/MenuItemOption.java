package com.sathira.yumcart.module.menu.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "menu_item_options")
public class MenuItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "option_group_id")
    private MenuItemOptionGroup optionGroup;

    @Column(nullable = true)
    private BigDecimal priceAdjustment; // Can be null if not affecting price
}
