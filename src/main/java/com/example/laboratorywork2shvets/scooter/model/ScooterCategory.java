package com.example.laboratorywork2shvets.scooter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scooter_categories")
public class ScooterCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Type is required")
    @Column(nullable = false, unique = true)
    private String type;

    @Column
    private String description;

    // One category has many scooters
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("category-scooters")
    private List<com.example.laboratorywork2shvets.scooter.model.Scooter> scooters = new ArrayList<>();

    public ScooterCategory() {}

    public ScooterCategory(Long id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<com.example.laboratorywork2shvets.scooter.model.Scooter> getScooters() { return scooters; }
    public void setScooters(List<com.example.laboratorywork2shvets.scooter.model.Scooter> scooters) { this.scooters = scooters; }
}

