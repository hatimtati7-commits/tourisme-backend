
    package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

    @Repository
    public interface BudgetRepository
            extends JpaRepository<Budget, Long> {

        Optional<Budget> findByTouristId(Long touristId);
    }

