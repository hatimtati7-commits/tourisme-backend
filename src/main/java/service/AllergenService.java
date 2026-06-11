package com.tourisme.tourisme_app.service;

import com.tourisme.tourisme_app.model.Food;
import com.tourisme.tourisme_app.model.Tourist;
import com.tourisme.tourisme_app.repository.FoodRepository;
import com.tourisme.tourisme_app.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllergenService {

    private final FoodRepository foodRepo;
    private final TouristRepository touristRepo;

    // Retourne les plats sans allergènes dangereux pour le touriste
    public List<Food> getSafeFoods(Long touristId) {
        Tourist tourist = touristRepo
                .findById(touristId).orElseThrow();

        List<String> allergies = tourist.getAllergies();

        return foodRepo.findAll().stream()
                .filter(food -> food.getIngredients()
                        .stream()
                        .noneMatch(allergies::contains))
                .collect(Collectors.toList());
    }

    // Vérifie si un plat est dangereux pour le touriste
    public boolean isFoodSafe(Long touristId, Long foodId) {
        Tourist tourist = touristRepo
                .findById(touristId).orElseThrow();
        Food food = foodRepo
                .findById(foodId).orElseThrow();

        List<String> allergies = tourist.getAllergies();
        return food.getIngredients().stream()
                .noneMatch(allergies::contains);
    }

    // Retourne les ingrédients dangereux d'un plat
    public List<String> getDangerousIngredients(
            Long touristId, Long foodId) {
        Tourist tourist = touristRepo
                .findById(touristId).orElseThrow();
        Food food = foodRepo
                .findById(foodId).orElseThrow();

        return food.getIngredients().stream()
                .filter(i -> tourist.getAllergies().contains(i))
                .collect(Collectors.toList());
    }
}