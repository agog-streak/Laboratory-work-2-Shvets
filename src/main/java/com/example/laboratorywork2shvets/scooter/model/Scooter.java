package com.example.laboratorywork2shvets.scooter.model;

import com.example.laboratorywork2shvets.scooter.model.ScooterCategory;
import com.example.laboratorywork2shvets.scooter.model.Station;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scooters")
public class Scooter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Model is required")
    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private boolean available = true;

    @Column(name = "battery_level")
    private Integer batteryLevel;

    @Column(name = "price_per_minute")
    private Double pricePerMinute;

    // Many scooters belong to one category
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonBackReference("category-scooters")
    private ScooterCategory category;

    // Many scooters can be at one station
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    @JsonBackReference("station-scooters")
    private Station currentStation;

    // One scooter can have many rides
    @OneToMany(mappedBy = "scooter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("scooter-rides")
    private List<Ride> rides = new ArrayList<>();

    public Scooter() {}

    public Scooter(Long id, String model, boolean available, Integer batteryLevel, Double pricePerMinute) {
        this.id = id;
        this.model = model;
        this.available = available;
        this.batteryLevel = batteryLevel;
        this.pricePerMinute = pricePerMinute;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public Integer getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(Integer batteryLevel) { this.batteryLevel = batteryLevel; }

    public Double getPricePerMinute() { return pricePerMinute; }
    public void setPricePerMinute(Double pricePerMinute) { this.pricePerMinute = pricePerMinute; }

    public ScooterCategory getCategory() { return category; }
    public void setCategory(ScooterCategory category) { this.category = category; }

    public Station getCurrentStation() { return currentStation; }
    public void setCurrentStation(Station currentStation) { this.currentStation = currentStation; }

    public List<com.example.laboratorywork2shvets.scooter.model.Ride> getRides() { return rides; }
    public void setRides(List<com.example.laboratorywork2shvets.scooter.model.Ride> rides) { this.rides = rides; }
}
