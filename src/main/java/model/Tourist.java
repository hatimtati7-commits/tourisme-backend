package com.tourisme.tourisme_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "tourists")
@Data
@NoArgsConstructor
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private int nbFavoris = 0;
    private int nbVilles = 0;
    private int nbVoyages = 0;

    @ElementCollection
    private List<String> preferences;

    @ElementCollection
    private List<String> allergies;
}