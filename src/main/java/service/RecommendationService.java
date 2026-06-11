package com.tourisme.tourisme_app.service;

import com.tourisme.tourisme_app.model.*;
import com.tourisme.tourisme_app.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final HotelRepository hotelRepo;
    private final ActivityRepository activityRepo;
    private final BudgetRepository budgetRepo;
    private final TouristRepository touristRepo;

    // Hôtels dans le budget du touriste
    public List<Hotel> getAffordableHotels(Long touristId) {
        Budget b = budgetRepo
                .findByTouristId(touristId).orElseThrow();
        return hotelRepo.findByPricePerNightLessThanEqual(
                b.getRemainingHotel()
        );
    }

    // Activités dans le budget + selon préférences
    public List<Activity> getRecommendedActivities(
            Long touristId) {
        Tourist tourist = touristRepo
                .findById(touristId).orElseThrow();
        Budget b = budgetRepo
                .findByTouristId(touristId).orElseThrow();

        List<Activity> affordable = activityRepo
                .findByPriceLessThanEqual(
                        b.getRemainingActivity());

        // Filtre selon les préférences du touriste
        if (tourist.getPreferences() != null
                && !tourist.getPreferences().isEmpty()) {
            return affordable.stream()
                    .filter(a -> tourist.getPreferences()
                            .contains(a.getType()))
                    .collect(Collectors.toList());
        }
        return affordable;
    }
}