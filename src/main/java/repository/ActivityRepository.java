package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository
        extends JpaRepository<Activity, Long> {

    List<Activity> findByPriceLessThanEqual(Double price);
    List<Activity> findByType(String type);
}