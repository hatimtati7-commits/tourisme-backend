package com.tourisme.tourisme_app.controller;

import com.tourisme.tourisme_app.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    // GET /api/route/{hotelId}?activities=1,2,3
    // Retourne l'itinéraire complet avec distances et temps
    @GetMapping("/{hotelId}")
    public ResponseEntity<Map<String, Object>> getItinerary(
            @PathVariable Long hotelId,
            @RequestParam List<Long> activities) {
        return ResponseEntity.ok(
                routeService.getItinerary(hotelId, activities)
        );
    }

    // GET /api/route/distance?lat1=..&lon1=..&lat2=..&lon2=..
    // Calcule la distance entre 2 points GPS
    @GetMapping("/distance")
    public ResponseEntity<Map<String, Object>> getDistance(
            @RequestParam double lat1,
            @RequestParam double lon1,
            @RequestParam double lat2,
            @RequestParam double lon2) {
        double dist = routeService
                .calculateDistance(lat1, lon1, lat2, lon2);
        double time = routeService.walkingTime(dist);
        return ResponseEntity.ok(Map.of(
                "distanceKm",
                Math.round(dist * 100.0) / 100.0,
                "walkingMinutes",
                Math.round(time)
        ));
    }
}