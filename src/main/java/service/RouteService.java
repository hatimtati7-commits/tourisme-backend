package com.tourisme.tourisme_app.service;

import com.tourisme.tourisme_app.model.Activity;
import com.tourisme.tourisme_app.model.Hotel;
import com.tourisme.tourisme_app.repository.ActivityRepository;
import com.tourisme.tourisme_app.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final HotelRepository hotelRepo;
    private final ActivityRepository activityRepo;

    // Calcule la distance en km entre 2 points GPS
    public double calculateDistance(
            double lat1, double lon1,
            double lat2, double lon2) {
        final int R = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(
                Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    // Calcule le temps de marche en minutes
    public double walkingTime(double distanceKm) {
        // Vitesse moyenne : 5 km/h
        return (distanceKm / 5.0) * 60;
    }

    // Calcule l'itinéraire complet depuis un hôtel
    public Map<String, Object> getItinerary(
            Long hotelId, List<Long> activityIds) {
        Hotel hotel = hotelRepo
                .findById(hotelId).orElseThrow();

        List<Activity> activities = new ArrayList<>();
        for (Long id : activityIds) {
            activityRepo.findById(id)
                    .ifPresent(activities::add);
        }

        List<Map<String, Object>> steps = new ArrayList<>();
        double totalDistance = 0;
        double totalTime = 0;

        double currentLat = hotel.getLatitude();
        double currentLon = hotel.getLongitude();

        for (Activity activity : activities) {
            double dist = calculateDistance(
                    currentLat, currentLon,
                    activity.getLatitude(),
                    activity.getLongitude());
            double time = walkingTime(dist);
            totalDistance += dist;
            totalTime += time;

            Map<String, Object> step = new HashMap<>();
            step.put("activity", activity.getName());
            step.put("distanceKm",
                    Math.round(dist * 100.0) / 100.0);
            step.put("walkingMinutes",
                    Math.round(time));
            steps.add(step);

            currentLat = activity.getLatitude();
            currentLon = activity.getLongitude();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("hotel", hotel.getName());
        result.put("steps", steps);
        result.put("totalDistanceKm",
                Math.round(totalDistance * 100.0) / 100.0);
        result.put("totalWalkingMinutes",
                Math.round(totalTime));
        return result;
    }
}