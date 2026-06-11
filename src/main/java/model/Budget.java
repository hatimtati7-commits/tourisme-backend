package com.tourisme.tourisme_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalBudget;
    private Double hotelBudget;
    private Double activityBudget;
    private Double foodBudget;

    private Double spentHotel = 0.0;
    private Double spentActivity = 0.0;
    private Double spentFood = 0.0;

    @ManyToOne
    @JoinColumn(name = "tourist_id")
    private Tourist tourist;

    public Double getRemainingHotel() {
        return hotelBudget - spentHotel;
    }

    public Double getRemainingActivity() {
        return activityBudget - spentActivity;
    }

    public Double getRemainingFood() {
        return foodBudget - spentFood;
    }
}