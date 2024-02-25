package com.sathira.yumcart.module.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String username; // Or link to a User entity

    @Column(nullable = false)
    private int rating;

    @Column(length = 1024)
    private String comment;

}
