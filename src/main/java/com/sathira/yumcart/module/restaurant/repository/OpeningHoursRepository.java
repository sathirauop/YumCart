package com.sathira.yumcart.module.restaurant.repository;

import com.sathira.yumcart.module.restaurant.model.OpeningHours;
import com.sathira.yumcart.module.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
}
