package com.example.laboratorywork2shvets.scooter.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator")
class ActuatorController {
    @GetMapping
    public ResponseEntity<String> actuator() {
        return ResponseEntity.ok("Actuator OK");
    }
}

