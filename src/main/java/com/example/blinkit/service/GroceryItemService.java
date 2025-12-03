package com.example.blinkit.service;

import com.example.blinkit.entity.GroceryItem;
import com.example.blinkit.repository.GroceryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemService {

    private final GroceryRepository repository;

    public GroceryItemService(GroceryRepository repository) {
        this.repository = repository;
    }

    public List<GroceryItem> searchItems(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return repository.findAll();
        }
        return repository.searchByKeyword(keyword);
    }

    public List<GroceryItem> getAllItems() {
        return repository.findAll();
    }

    public GroceryItem saveItem(GroceryItem item) {
        return repository.save(item);
    }

    public GroceryItem getItemById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }
}
