package com.example.laboratorywork2shvets.scooter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Station name is required")
    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private Integer capacity;

    // One station can have many scooters parked
    @OneToMany(mappedBy = "currentStation", fetch = FetchType.LAZY)
    @JsonManagedReference("station-scooters")
    private List<com.example.laboratorywork2shvets.scooter.model.Scooter> scooters = new ArrayList<>();

    public Station() {}

    public Station(Long id, String name, String address, Integer capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public List<com.example.laboratorywork2shvets.scooter.model.Scooter> getScooters() { return scooters; }
    public void setScooters(List<com.example.laboratorywork2shvets.scooter.model.Scooter> scooters) { this.scooters = scooters; }
}
