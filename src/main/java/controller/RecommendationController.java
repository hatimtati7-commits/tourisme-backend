package com.tourisme.tourisme_app.controller;

import com.tourisme.tourisme_app.model.Activity;
import com.tourisme.tourisme_app.model.Hotel;
import com.tourisme.tourisme_app.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // GET /api/recommend/hotels/{touristId}
    // Retourne les hôtels dans le budget du touriste
    @GetMapping("/hotels/{touristId}")
    public ResponseEntity<List<Hotel>> getHotels(
            @PathVariable Long touristId) {
        return ResponseEntity.ok(
                recommendationService
                        .getAffordableHotels(touristId)
        );
    }

    // GET /api/recommend/activities/{touristId}
    // Retourne les activités selon budget + préférences
    @GetMapping("/activities/{touristId}")
    public ResponseEntity<List<Activity>> getActivities(
            @PathVariable Long touristId) {
        return ResponseEntity.ok(
                recommendationService
                        .getRecommendedActivities(touristId)
        );
    }
}