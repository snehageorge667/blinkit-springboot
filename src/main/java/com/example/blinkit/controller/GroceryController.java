package com.example.blinkit.controller;

import com.example.blinkit.entity.GroceryItem;
import com.example.blinkit.repository.GroceryItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groceries")
public class GroceryController {

    private final GroceryItemRepository repository;

    public GroceryController(GroceryItemRepository repository) {
        this.repository = repository;
    }

    // Get all items
    @GetMapping
    public List<GroceryItem> getAll() {
        return repository.findAll();
    }

    // Search by name or category
    @GetMapping("/search")
    public List<GroceryItem> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category) {

        if (name != null && !name.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(name);
        } else if (category != null && !category.isEmpty()) {
            return repository.findByCategoryIgnoreCase(category);
        } else {
            return repository.findAll();
        }
    }
}
