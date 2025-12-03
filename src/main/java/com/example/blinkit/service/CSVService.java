package com.example.blinkit.service;

import com.example.blinkit.entity.GroceryItem;
import com.example.blinkit.repository.GroceryRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class CSVService {

    private final GroceryRepository repository;

    public CSVService(GroceryRepository repository) {
        this.repository = repository;
    }

    public void importCSV(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            boolean skipHeader = true; // skip first row (header)
            
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // skip empty lines
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] data = line.split(",");
                
                if (data.length < 4) {
                    System.out.println("Skipping invalid line: " + line);
                    continue; // skip malformed lines
                }

                GroceryItem item = new GroceryItem();
                item.setName(data[0].trim());
                item.setDescription(data[1].trim());
                item.setCategory(data[2].trim());
                item.setPrice(Double.parseDouble(data[3].trim()));

                repository.save(item);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV", e);
        }
    }
}
