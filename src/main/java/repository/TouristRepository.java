
    package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface TouristRepository
            extends JpaRepository<Tourist, Long> {
    }

