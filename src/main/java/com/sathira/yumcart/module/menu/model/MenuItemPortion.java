package com.sathira.yumcart.module.menu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_item_portions")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MenuItemPortion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "Half", "Full"

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "menu_item_id",
            foreignKey = @ForeignKey(
                    name = "menuItem_id_fk"
            )
    )
    private MenuItem menuItem;

    public MenuItemPortion(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
