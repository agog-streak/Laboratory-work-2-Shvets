package com.example.laboratorywork2shvets.scooter.repository;

import com.example.laboratorywork2shvets.scooter.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByUserId(Long userId);

    List<Ride> findByScooterId(Long scooterId);

    List<Ride> findByStatus(String status);

    Optional<Ride> findByUserIdAndStatus(Long userId, String status);

    List<Ride> findByUserIdOrderByStartTimeDesc(Long userId);
}
