package com.example.laboratorywork2shvets.scooter.controller;

import com.example.laboratorywork2shvets.scooter.model.Scooter;
import com.example.laboratorywork2shvets.scooter.model.ScooterCategory;
import com.example.laboratorywork2shvets.scooter.service.ScooterCategoryService;
import com.example.laboratorywork2shvets.scooter.service.ScooterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class ScooterCategoryController {

    private final ScooterCategoryService categoryService;
    private final ScooterService scooterService;

    public ScooterCategoryController(ScooterCategoryService categoryService,
                                     ScooterService scooterService) {
        this.categoryService = categoryService;
        this.scooterService = scooterService;
    }

    // GET /categories - отримати всі категорії
    @GetMapping
    public ResponseEntity<List<ScooterCategory>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // GET /categories/{id} - отримати категорію за id
    @GetMapping("/{id}")
    public ResponseEntity<ScooterCategory> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    // POST /categories - створити категорію
    @PostMapping
    public ResponseEntity<ScooterCategory> createCategory(@RequestBody ScooterCategory category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(category));
    }

    // PUT /categories/{id} - оновити категорію
    @PutMapping("/{id}")
    public ResponseEntity<ScooterCategory> updateCategory(@PathVariable Long id,
                                                          @RequestBody ScooterCategory category) {
        return ResponseEntity.ok(categoryService.updateCategory(id, category));
    }

    // DELETE /categories/{id} - видалити категорію
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // ============ CROSS-ENTITY ENDPOINTS ============

    // GET /categories/{id}/scooters - отримати всі самокати категорії
    @GetMapping("/{id}/scooters")
    public ResponseEntity<List<Scooter>> getScootersByCategory(@PathVariable Long id) {
        categoryService.getCategoryById(id); // перевіряємо що категорія існує
        return ResponseEntity.ok(scooterService.getScootersByCategory(id));
    }
}
