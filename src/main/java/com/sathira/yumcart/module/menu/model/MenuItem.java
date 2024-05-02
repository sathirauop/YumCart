package com.sathira.yumcart.module.menu.model;

import com.sathira.yumcart.module.restaurant.model.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "MenuItem")
@Table(
        name = "MenuItems",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "menuItemName",
                        columnNames = "name"
                )
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name ="description", length = 1024) // Assuming description can be lengthy
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id",
            foreignKey = @ForeignKey(
                    name = "category_id_fk"
            )
    )
    private Category category;

    @OneToMany(mappedBy = "menuItem", orphanRemoval = false)
    @Column(name = "portions")
    private List<MenuItemPortion> portions = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "menu_item_dietary_labels",
            joinColumns = @JoinColumn(name = "menu_item_id"),
            inverseJoinColumns = @JoinColumn(name = "dietary_label_id")
    )
    private List<DietaryLabel> dietaryLabels = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "restaurant_id",
            foreignKey = @ForeignKey(
                    name = "restaurent_id_fk"
            )
    )
    private Restaurant restaurant;

    public MenuItem(String name, String description, BigDecimal price, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public void addPortion(MenuItemPortion portion){
        if(!portions.contains(portion)){
            portions.add(portion);
            portion.setMenuItem(this);
        }
    }

    // TODO : Need to update some annotations. better to add names for foriegn keys likewise.
    // TODO: JoinColumn in ManyToOne may have some attributes. Find what referencedColumnName attribute inside @JoinColumn

}
