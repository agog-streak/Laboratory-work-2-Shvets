package com.example.laboratorywork2shvets.scooter.controller;

import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.model.Station;
import com.example.laboratorywork2shvets.scooter.service.ScooterService;
import com.example.laboratorywork2shvets.scooter.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    private final StationService stationService;
    private final ScooterService scooterService;

    public StationController(StationService stationService, ScooterService scooterService) {
        this.stationService = stationService;
        this.scooterService = scooterService;
    }

    // GET /stations - отримати всі станції
    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }

    // GET /stations/{id} - отримати станцію за id
    @GetMapping("/{id}")
    public ResponseEntity<Station> getStation(@PathVariable Long id) {
        return ResponseEntity.ok(stationService.getStationById(id));
    }

    // POST /stations - створити станцію
    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stationService.createStation(station));
    }

    // PUT /stations/{id} - оновити станцію
    @PutMapping("/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station station) {
        return ResponseEntity.ok(stationService.updateStation(id, station));
    }

    // DELETE /stations/{id} - видалити станцію
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    // ============ CROSS-ENTITY ENDPOINTS ============

    // GET /stations/{id}/scooters - отримати всі самокати на станції
    @GetMapping("/{id}/scooters")
    public ResponseEntity<List<Scooter>> getScootersByStation(@PathVariable Long id) {
        stationService.getStationById(id); // перевіряємо що станція існує
        return ResponseEntity.ok(scooterService.getScootersByStation(id));
    }
}


