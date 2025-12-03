package com.example.blinkit.controller;

import com.example.blinkit.entity.GroceryItem;
import com.example.blinkit.repository.GroceryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {

    private final GroceryRepository repository;

    public GroceryController(GroceryRepository repository) {
        this.repository = repository;
    }

    // Get all items
    @GetMapping
    public List<GroceryItem> getAllItems() {
        return repository.findAll();
    }

    // Get item by ID
    @GetMapping("/{id}")
    public GroceryItem getItemById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));
    }

    // Create new item
    @PostMapping
    public GroceryItem createItem(@RequestBody GroceryItem item) {
        return repository.save(item);
    }

    // Update existing item
    @PutMapping("/{id}")
    public GroceryItem updateItem(@PathVariable Long id, @RequestBody GroceryItem itemDetails) {
        GroceryItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with ID: " + id));

        item.setItemFatContent(itemDetails.getItemFatContent());
        item.setItemIdentifier(itemDetails.getItemIdentifier());
        item.setItemType(itemDetails.getItemType());
        item.setOutletEstablishmentYear(itemDetails.getOutletEstablishmentYear());
        item.setOutletLocationType(itemDetails.getOutletLocationType());
        item.setSales(itemDetails.getSales());

        return repository.save(item);
    }

    // Delete item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
        return "Item deleted with ID: " + id;
    }

    // Universal search endpoint
    @GetMapping("/search")
    public List<GroceryItem> searchItems(@RequestParam("keyword") String keyword) {
        return repository.searchByKeyword(keyword);
    }
}
