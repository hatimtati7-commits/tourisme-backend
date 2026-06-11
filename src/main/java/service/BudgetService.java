
    package com.tourisme.tourisme_app.service;

import com.tourisme.tourisme_app.model.Budget;
import com.tourisme.tourisme_app.model.Tourist;
import com.tourisme.tourisme_app.repository.BudgetRepository;
import com.tourisme.tourisme_app.repository.TouristRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class BudgetService {

        private final BudgetRepository budgetRepo;
        private final TouristRepository touristRepo;

        public Budget createBudget(Long touristId, Double total) {
            Tourist tourist = touristRepo.findById(touristId).orElseThrow();

            Budget budget = new Budget();
            budget.setTourist(tourist);
            budget.setTotalBudget(total);
            budget.setHotelBudget(total * 0.40);
            budget.setActivityBudget(total * 0.30);
            budget.setFoodBudget(total * 0.30);

            return budgetRepo.save(budget);
        }

        public boolean canAffordHotel(Long touristId, Double price) {
            Budget b = budgetRepo.findByTouristId(touristId).orElseThrow();
            return b.getRemainingHotel() >= price;
        }

        public Budget spend(Long touristId, String category, Double amount) {
            Budget b = budgetRepo.findByTouristId(touristId).orElseThrow();
            switch (category) {
                case "hotel"    -> b.setSpentHotel(b.getSpentHotel() + amount);
                case "activity" -> b.setSpentActivity(b.getSpentActivity() + amount);
                case "food"     -> b.setSpentFood(b.getSpentFood() + amount);
            }
            return budgetRepo.save(b);
        }

        public Budget getBudget(Long touristId) {
            return budgetRepo.findByTouristId(touristId).orElseThrow();
        }
    }

