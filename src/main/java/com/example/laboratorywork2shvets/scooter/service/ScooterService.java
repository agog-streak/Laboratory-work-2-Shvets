package com.example.laboratorywork2shvets.scooter.service;

import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.model.ScooterCategory;
import com.example.laboratorywork2shvets.scooter.model.Station;
import com.example.laboratorywork2shvets.scooter.repository.ScooterCategoryRepository;
import com.example.laboratorywork2shvets.scooter.repository.ScooterRepository;
import com.example.laboratorywork2shvets.scooter.repository.StationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScooterService {

    private final ScooterRepository scooterRepository;
    private final ScooterCategoryRepository categoryRepository;
    private final StationRepository stationRepository;

    public ScooterService(ScooterRepository scooterRepository,
                          ScooterCategoryRepository categoryRepository,
                          StationRepository stationRepository) {
        this.scooterRepository = scooterRepository;
        this.categoryRepository = categoryRepository;
        this.stationRepository = stationRepository;
    }

    public List<Scooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    public Scooter getScooterById(Long id) {
        return scooterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scooter not found with id: " + id));
    }

    public List<Scooter> getAvailableScooters() {
        return scooterRepository.findByAvailableTrue();
    }

    public List<Scooter> getScootersByCategory(Long categoryId) {
        return scooterRepository.findByCategoryId(categoryId);
    }

    public List<Scooter> getScootersByStation(Long stationId) {
        return scooterRepository.findByCurrentStationId(stationId);
    }

    public Scooter createScooter(Scooter scooter, Long categoryId, Long stationId) {
        if (categoryId != null) {
            ScooterCategory category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
            scooter.setCategory(category);
        }
        if (stationId != null) {
            Station station = stationRepository.findById(stationId)
                    .orElseThrow(() -> new RuntimeException("Station not found: " + stationId));
            scooter.setCurrentStation(station);
        }
        return scooterRepository.save(scooter);
    }

    public Scooter updateScooter(Long id, Scooter updated, Long categoryId, Long stationId) {
        Scooter existing = getScooterById(id);
        existing.setModel(updated.getModel());
        existing.setAvailable(updated.isAvailable());
        existing.setBatteryLevel(updated.getBatteryLevel());
        existing.setPricePerMinute(updated.getPricePerMinute());

        if (categoryId != null) {
            ScooterCategory cat = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));
            existing.setCategory(cat);
        }
        if (stationId != null) {
            Station station = stationRepository.findById(stationId)
                    .orElseThrow(() -> new RuntimeException("Station not found: " + stationId));
            existing.setCurrentStation(station);
        }
        return scooterRepository.save(existing);
    }

    public void deleteScooter(Long id) {
        getScooterById(id);
        scooterRepository.deleteById(id);
    }
}
