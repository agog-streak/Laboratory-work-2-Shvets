package com.example.laboratorywork2shvets.scooter.repository;

import com.example.laboratorywork2shvets.scooter.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    List<Scooter> findByAvailableTrue();

    List<Scooter> findByCategoryId(Long categoryId);

    List<Scooter> findByCurrentStationId(Long stationId);

    @Query("SELECT s FROM Scooter s WHERE s.available = true AND s.batteryLevel >= :minBattery")
    List<Scooter> findAvailableWithMinBattery(@Param("minBattery") int minBattery);
}
