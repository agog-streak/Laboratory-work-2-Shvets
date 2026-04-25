package com.example.laboratorywork2shvets.scooter.repository;

import com.example.laboratorywork2shvets.scooter.model.ScooterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ScooterCategoryRepository extends JpaRepository<ScooterCategory, Long> {
    Optional<ScooterCategory> findByType(String type);
}
