package com.example.laboratorywork2shvets.scooter.service;

import com.example.laboratorywork2shvets.scooter.model.Ride;
import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.model.Station;
import com.example.laboratorywork2shvets.scooter.model.User;
import com.example.laboratorywork2shvets.scooter.repository.RideRepository;
import com.example.laboratorywork2shvets.scooter.repository.ScooterRepository;
import com.example.laboratorywork2shvets.scooter.repository.StationRepository;
import com.example.laboratorywork2shvets.scooter.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RideService {

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final ScooterRepository scooterRepository;
    private final StationRepository stationRepository;

    public RideService(RideRepository rideRepository,
                       UserRepository userRepository,
                       ScooterRepository scooterRepository,
                       StationRepository stationRepository) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.scooterRepository = scooterRepository;
        this.stationRepository = stationRepository;
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with id: " + id));
    }

    public List<Ride> getRidesByUser(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        return rideRepository.findByUserIdOrderByStartTimeDesc(userId);
    }

    public List<Ride> getRidesByScooter(Long scooterId) {
        scooterRepository.findById(scooterId)
                .orElseThrow(() -> new RuntimeException("Scooter not found: " + scooterId));
        return rideRepository.findByScooterId(scooterId);
    }

    @Transactional
    public Ride startRide(Long userId, Long scooterId, Long startStationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Scooter scooter = scooterRepository.findById(scooterId)
                .orElseThrow(() -> new RuntimeException("Scooter not found: " + scooterId));

        if (!scooter.isAvailable()) {
            throw new RuntimeException("Scooter is not available");
        }

        // Check if user already has an active ride
        rideRepository.findByUserIdAndStatus(userId, "ACTIVE").ifPresent(r -> {
            throw new RuntimeException("User already has an active ride");
        });

        Ride ride = new Ride();
        ride.setUser(user);
        ride.setScooter(scooter);
        ride.setStartTime(LocalDateTime.now());
        ride.setStatus("ACTIVE");

        if (startStationId != null) {
            Station startStation = stationRepository.findById(startStationId)
                    .orElseThrow(() -> new RuntimeException("Station not found: " + startStationId));
            ride.setStartStation(startStation);
        }

        // Mark scooter as unavailable
        scooter.setAvailable(false);
        scooterRepository.save(scooter);

        return rideRepository.save(ride);
    }

    @Transactional
    public Ride endRide(Long rideId, Long endStationId) {
        Ride ride = getRideById(rideId);

        if (!"ACTIVE".equals(ride.getStatus())) {
            throw new RuntimeException("Ride is not active");
        }

        ride.setEndTime(LocalDateTime.now());
        ride.setStatus("COMPLETED");

        // Calculate total cost
        long minutes = ChronoUnit.MINUTES.between(ride.getStartTime(), ride.getEndTime());
        Double pricePerMin = ride.getScooter().getPricePerMinute();
        if (pricePerMin != null) {
            ride.setTotalCost(minutes * pricePerMin);
        }

        if (endStationId != null) {
            Station endStation = stationRepository.findById(endStationId)
                    .orElseThrow(() -> new RuntimeException("Station not found: " + endStationId));
            ride.setEndStation(endStation);

            // Move scooter to end station
            ride.getScooter().setCurrentStation(endStation);
        }

        // Mark scooter as available again
        ride.getScooter().setAvailable(true);
        scooterRepository.save(ride.getScooter());

        return rideRepository.save(ride);
    }

    public void deleteRide(Long id) {
        getRideById(id);
        rideRepository.deleteById(id);
    }
}
