package com.tourisme.tourisme_app.repository;

import com.tourisme.tourisme_app.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HotelRepository
        extends JpaRepository<Hotel, Long> {

    List<Hotel> findByPricePerNightLessThanEqual(Double price);
}