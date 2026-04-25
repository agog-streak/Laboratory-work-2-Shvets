package com.example.laboratorywork2shvets.scooter.service;

import com.example.laboratorywork2shvets.scooter.model.Station;
import com.example.laboratorywork2shvets.scooter.repository.StationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
    }

    public Station createStation(Station station) {
        return stationRepository.save(station);
    }

    public Station updateStation(Long id, Station updated) {
        Station existing = getStationById(id);
        existing.setName(updated.getName());
        existing.setAddress(updated.getAddress());
        existing.setCapacity(updated.getCapacity());
        return stationRepository.save(existing);
    }

    public void deleteStation(Long id) {
        getStationById(id);
        stationRepository.deleteById(id);
    }
}
