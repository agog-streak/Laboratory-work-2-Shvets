package com.example.laboratorywork2shvets.scooter.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
class AnalyticsController {
    @GetMapping
    public ResponseEntity<String> analytics() {
        return ResponseEntity.ok("Analytics OK");
    }
}
