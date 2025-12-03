package com.example.blinkit.config;

import com.example.blinkit.entity.GroceryItem;
import com.example.blinkit.repository.GroceryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
public class DataLoader {

    private final GroceryRepository repository;

    public DataLoader(GroceryRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadCSV() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("groceries.csv")),
                        StandardCharsets.UTF_8))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }

                String[] values = line.split(",");

                if (values.length < 6) continue; // skip invalid lines

                GroceryItem item = new GroceryItem();
                item.setItemFatContent(values[0].trim());
                item.setItemIdentifier(values[1].trim());
                item.setItemType(values[2].trim());
                item.setOutletEstablishmentYear(Integer.parseInt(values[3].trim()));
                item.setOutletLocationType(values[4].trim());
                item.setSales(Double.parseDouble(values[5].trim()));

                repository.save(item);
            }

            System.out.println("CSV data loaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
