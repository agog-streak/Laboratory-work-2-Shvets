package com.example.laboratorywork2shvets.scooter.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
class SearchController {
    @GetMapping
    public ResponseEntity<List<String>> search() {
        List<String> result = List.of(
                "Scooter Xiaomi",
                "Scooter Ninebot",
                "Station Center"
        );
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<String> searchPost() {
        return ResponseEntity.ok("POST /search OK");
    }
}
