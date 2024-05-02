    package com.sathira.yumcart.module.restaurant.model;

    import com.fasterxml.jackson.annotation.JsonManagedReference;
    import com.sathira.yumcart.module.menu.model.Category;
    import com.sathira.yumcart.module.menu.model.MenuItem;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Set;

    @Entity(name = "Restaurants")
    @Table(name = "restaurants")
    @AllArgsConstructor
    @NoArgsConstructor
    @RequiredArgsConstructor
    @Getter
    @Setter
    public class Restaurant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        @NonNull
        private String name;

        @Column(nullable = false)
        @NonNull
        private String address;

        @Column(nullable = false)
        @NonNull
        private String phoneNumber;

        @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = false)
        private List<OpeningHours> openingHours = new ArrayList<>();

        @OneToMany(mappedBy = "restaurant", orphanRemoval = false)
        private List<MenuItem> menuItems = new ArrayList<>();

        @OneToMany(mappedBy = "restaurant", orphanRemoval = false)
        private List<Review> reviews = new ArrayList<>();

        @ManyToMany
        @JoinTable(
                name = "restaurant_category",
                joinColumns = @JoinColumn(
                        name = "restaurant_id",
                        foreignKey = @ForeignKey(name = "restaurant_fk")
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "category_id",
                        foreignKey = @ForeignKey(name = "category_fk")
                )
        )
        private List<Category> categories = new ArrayList<>();

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
        public  void removeCategory(Category category){
            categories.remove(category);
            category.getRestaurants().remove(this);
        }

        //TODO : Need to assign a empty ArrayList List items like List<MenuItem> menuItems

    }
