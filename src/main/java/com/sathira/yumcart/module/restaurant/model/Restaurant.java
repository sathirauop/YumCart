    package com.sathira.yumcart.module.restaurant.model;

    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import com.sathira.yumcart.module.menu.model.Category;
    import com.sathira.yumcart.module.menu.model.MenuItem;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Set;

    @Entity
    @Table(name = "restaurants")
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String name;

        @Column(nullable = false)
        private String address;

        @Column(nullable = false)
        private String phoneNumber;

        @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
        private List<OpeningHours> openingHours;

        @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
        private List<MenuItem> menuItems;

        @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
        private List<Review> reviews;

        @ManyToMany
        @JoinTable(
                name = "restaurant_category",
                joinColumns = @JoinColumn(name = "restaurant_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id")
        )
        private List<Category> categories;

        public void addMenuItem(MenuItem menuItem) {
            menuItems.add(menuItem);
            menuItem.setRestaurant(this);
        }


        public void removeMenuItem(MenuItem menuItem) {
            menuItems.remove(menuItem);
            menuItem.setRestaurant(null);
        }

        public void addCategory(Category category) {
            categories.add(category);
            category.getRestaurants().add(this);
        }


    }
