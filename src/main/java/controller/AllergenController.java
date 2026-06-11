package com.tourisme.tourisme_app.controller;

import com.tourisme.tourisme_app.model.Food;
import com.tourisme.tourisme_app.service.AllergenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/allergen")
@RequiredArgsConstructor
public class AllergenController {

    private final AllergenService allergenService;

    // GET /api/allergen/safe/{touristId}
    // Retourne tous les plats sans danger pour ce touriste
    @GetMapping("/safe/{touristId}")
    public ResponseEntity<List<Food>> getSafeFoods(
            @PathVariable Long touristId) {
        return ResponseEntity.ok(
                allergenService.getSafeFoods(touristId)
        );
    }

    // GET /api/allergen/check/{touristId}/{foodId}
    // Vérifie si un plat est safe pour ce touriste
    @GetMapping("/check/{touristId}/{foodId}")
    public ResponseEntity<Boolean> isFoodSafe(
            @PathVariable Long touristId,
            @PathVariable Long foodId) {
        return ResponseEntity.ok(
                allergenService.isFoodSafe(touristId, foodId)
        );
    }

    // GET /api/allergen/dangerous/{touristId}/{foodId}
    // Retourne les ingrédients dangereux du plat
    @GetMapping("/dangerous/{touristId}/{foodId}")
    public ResponseEntity<List<String>> getDangerous(
            @PathVariable Long touristId,
            @PathVariable Long foodId) {
        return ResponseEntity.ok(
                allergenService.getDangerousIngredients(
                        touristId, foodId)
        );
    }
}