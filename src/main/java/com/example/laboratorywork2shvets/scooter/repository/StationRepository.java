package com.example.laboratorywork2shvets.scooter.repository;

import com.example.laboratorywork2shvets.scooter.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    List<Station> findByNameContainingIgnoreCase(String name);
}
