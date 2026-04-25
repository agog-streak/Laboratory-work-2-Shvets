package com.example.laboratorywork2shvets.scooter.controller;

import com.example.laboratorywork2shvets.scooter.model.Ride;
import com.example.laboratorywork2shvets.scooter.model.User;
import com.example.laboratorywork2shvets.scooter.service.RideService;
import com.example.laboratorywork2shvets.scooter.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RideService rideService;

    public UserController(UserService userService, RideService rideService) {
        this.userService = userService;
        this.rideService = rideService;
    }

    // GET /users - отримати всіх користувачів
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // GET /users/{id} - отримати користувача за id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // POST /users - створити нового користувача
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    // PUT /users/{id} - оновити користувача
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    // DELETE /users/{id} - видалити користувача
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // ============ CROSS-ENTITY ENDPOINTS ============

    // GET /users/{id}/rides - отримати всі поїздки користувача
    @GetMapping("/{id}/rides")
    public ResponseEntity<List<Ride>> getUserRides(@PathVariable Long id) {
        return ResponseEntity.ok(rideService.getRidesByUser(id));
    }
}
