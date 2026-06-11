
    package com.tourisme.tourisme_app.controller;

import com.tourisme.tourisme_app.model.Budget;
import com.tourisme.tourisme_app.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/budget")
    @RequiredArgsConstructor
    public class BudgetController {

        private final BudgetService budgetService;

        @PostMapping("/create/{touristId}/{total}")
        public ResponseEntity<Budget> create(
                @PathVariable Long touristId,
                @PathVariable Double total) {
            return ResponseEntity.ok(
                    budgetService.createBudget(touristId, total)
            );
        }

        @GetMapping("/{touristId}")
        public ResponseEntity<Budget> getBudget(
                @PathVariable Long touristId) {
            return ResponseEntity.ok(
                    budgetService.getBudget(touristId)
            );
        }

        @PostMapping("/spend/{touristId}/{category}/{amount}")
        public ResponseEntity<Budget> spend(
                @PathVariable Long touristId,
                @PathVariable String category,
                @PathVariable Double amount) {
            return ResponseEntity.ok(
                    budgetService.spend(touristId, category, amount)
            );
        }
    }

