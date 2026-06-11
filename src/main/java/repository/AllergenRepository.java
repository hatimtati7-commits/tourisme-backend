package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Allergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository
        extends JpaRepository<Allergen, Long> {
}