package com.example.blinkit.repository;

import com.example.blinkit.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {

    // Universal search across all required columns
    @Query("SELECT g FROM GroceryItem g WHERE " +
            "LOWER(g.itemFatContent) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
            "LOWER(g.itemIdentifier) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
            "LOWER(g.itemType) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
            "CAST(g.outletEstablishmentYear AS string) LIKE CONCAT('%', :key, '%') OR " +
            "LOWER(g.outletLocationType) LIKE LOWER(CONCAT('%', :key, '%')) OR " +
            "CAST(g.sales AS string) LIKE CONCAT('%', :key, '%')")
    List<GroceryItem> searchByKeyword(@Param("key") String keyword);
}
