package com.example.laboratorywork2shvets.scooter.service;

import com.example.laboratorywork2shvets.scooter.model.ScooterCategory;
import com.example.laboratorywork2shvets.scooter.repository.ScooterCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScooterCategoryService {

    private final ScooterCategoryRepository categoryRepository;

    public ScooterCategoryService(ScooterCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ScooterCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ScooterCategory getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public ScooterCategory createCategory(ScooterCategory category) {
        return categoryRepository.save(category);
    }

    public ScooterCategory updateCategory(Long id, ScooterCategory updated) {
        ScooterCategory existing = getCategoryById(id);
        existing.setType(updated.getType());
        existing.setDescription(updated.getDescription());
        return categoryRepository.save(existing);
    }

    public void deleteCategory(Long id) {
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }
}
