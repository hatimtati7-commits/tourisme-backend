package com.tourisme.tourisme_app.controller;

import com.tourisme.tourisme_app.model.Tourist;
import com.tourisme.tourisme_app.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tourist")
@RequiredArgsConstructor
public class TouristController {

    private final TouristRepository touristRepo;

    @PostMapping("/create")
    public ResponseEntity<Tourist> create(
            @RequestBody Tourist tourist) {
        return ResponseEntity.ok(touristRepo.save(tourist));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Tourist loginRequest) {
        return touristRepo.findByEmail(loginRequest.getEmail())
                .filter(t -> t.getPassword().equals(loginRequest.getPassword()))
                .map(t -> ResponseEntity.ok((Object) t))
                .orElse(ResponseEntity.status(401).body("Email ou mot de passe incorrect"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tourist> getById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                touristRepo.findById(id).orElseThrow()
        );
    }

    @PutMapping("/{id}/allergies")
    public ResponseEntity<Tourist> updateAllergies(
            @PathVariable Long id,
            @RequestBody List<String> allergies) {
        Tourist t = touristRepo.findById(id).orElseThrow();
        t.setAllergies(allergies);
        return ResponseEntity.ok(touristRepo.save(t));
    }

    @PutMapping("/{id}/preferences")
    public ResponseEntity<Tourist> updatePreferences(
            @PathVariable Long id,
            @RequestBody List<String> preferences) {
        Tourist t = touristRepo.findById(id).orElseThrow();
        t.setPreferences(preferences);
        return ResponseEntity.ok(touristRepo.save(t));
    }
}