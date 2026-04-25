package com.example.laboratorywork2shvets.scooter.controller;

import com.example.laboratorywork2shvets.scooter.model.Ride;
import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.service.RideService;
import com.example.laboratorywork2shvets.scooter.service.ScooterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/scooters")
public class ScooterController {

    private final ScooterService scooterService;
    private final RideService rideService;

    public ScooterController(ScooterService scooterService, RideService rideService) {
        this.scooterService = scooterService;
        this.rideService = rideService;
    }

    // GET /scooters - отримати всі самокати
    @GetMapping
    public ResponseEntity<List<Scooter>> getAllScooters() {
        return ResponseEntity.ok(scooterService.getAllScooters());
    }

    // GET /scooters/available - отримати доступні самокати
    @GetMapping("/available")
    public ResponseEntity<List<Scooter>> getAvailableScooters() {
        return ResponseEntity.ok(scooterService.getAvailableScooters());
    }

    // GET /scooters/{id} - отримати самокат за id
    @GetMapping("/{id}")
    public ResponseEntity<Scooter> getScooter(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.getScooterById(id));
    }

    // POST /scooters?categoryId=1&stationId=1 - створити самокат
    @PostMapping
    public ResponseEntity<Scooter> createScooter(
            @RequestBody Scooter scooter,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long stationId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(scooterService.createScooter(scooter, categoryId, stationId));
    }

    // PUT /scooters/{id}?categoryId=1&stationId=1 - оновити самокат
    @PutMapping("/{id}")
    public ResponseEntity<Scooter> updateScooter(
            @PathVariable Long id,
            @RequestBody Scooter scooter,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long stationId) {
        return ResponseEntity.ok(scooterService.updateScooter(id, scooter, categoryId, stationId));
    }

    // DELETE /scooters/{id} - видалити самокат
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable Long id) {
        scooterService.deleteScooter(id);
        return ResponseEntity.noContent().build();
    }

    // ============ CROSS-ENTITY ENDPOINTS ============

    // GET /scooters/{id}/rides - отримати всі поїздки самоката
    @GetMapping("/{id}/rides")
    public ResponseEntity<List<Ride>> getScooterRides(@PathVariable Long id) {
        return ResponseEntity.ok(rideService.getRidesByScooter(id));
    }
}