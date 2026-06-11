package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository
        extends JpaRepository<Food, Long> {

    List<Food> findByPriceLessThanEqual(Double price);
}