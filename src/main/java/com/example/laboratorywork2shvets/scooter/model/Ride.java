package com.example.laboratorywork2shvets.scooter.model;

import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.model.Station;
import com.example.laboratorywork2shvets.scooter.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column
    private String status; // ACTIVE, COMPLETED, CANCELLED

    // Many rides belong to one user
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-rides")
    @NotNull(message = "User is required")
    private User user;

    // Many rides belong to one scooter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id", nullable = false)
    @JsonBackReference("scooter-rides")
    @NotNull(message = "Scooter is required")
    private Scooter scooter;

    // Start station (where ride began)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "start_station_id")
    private Station startStation;

    // End station (where ride ended)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "end_station_id")
    private Station endStation;

    public Ride() {}

    public Ride(Long id, LocalDateTime startTime, LocalDateTime endTime,
                Double totalCost, String status) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Scooter getScooter() { return scooter; }
    public void setScooter(Scooter scooter) { this.scooter = scooter; }

    public Station getStartStation() { return startStation; }
    public void setStartStation(Station startStation) { this.startStation = startStation; }

    public Station getEndStation() { return endStation; }
    public void setEndStation(Station endStation) { this.endStation = endStation; }
}
