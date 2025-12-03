package com.example.blinkit.repository;

import com.example.blinkit.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {

    // Search by name ignoring case
    List<GroceryItem> findByNameContainingIgnoreCase(String name);

    // Search by category ignoring case
    List<GroceryItem> findByCategoryIgnoreCase(String category);

    // Optional: search across name, description, and category
    List<GroceryItem> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
        String name, String description, String category
    );
}
