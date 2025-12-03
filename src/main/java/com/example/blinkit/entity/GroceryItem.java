package com.example.blinkit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "grocery_items")
public class GroceryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemFatContent;
    private String itemIdentifier;
    private String itemType;
    private Integer outletEstablishmentYear;
    private String outletLocationType;
    private Double sales;

    // Default constructor
    public GroceryItem() {}

    // Constructor with fields
    public GroceryItem(String itemFatContent, String itemIdentifier, String itemType,
                       Integer outletEstablishmentYear, String outletLocationType, Double sales) {
        this.itemFatContent = itemFatContent;
        this.itemIdentifier = itemIdentifier;
        this.itemType = itemType;
        this.outletEstablishmentYear = outletEstablishmentYear;
        this.outletLocationType = outletLocationType;
        this.sales = sales;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemFatContent() {
        return itemFatContent;
    }

    public void setItemFatContent(String itemFatContent) {
        this.itemFatContent = itemFatContent;
    }

    public String getItemIdentifier() {
        return itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        this.itemIdentifier = itemIdentifier;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getOutletEstablishmentYear() {
        return outletEstablishmentYear;
    }

    public void setOutletEstablishmentYear(Integer outletEstablishmentYear) {
        this.outletEstablishmentYear = outletEstablishmentYear;
    }

    public String getOutletLocationType() {
        return outletLocationType;
    }

    public void setOutletLocationType(String outletLocationType) {
        this.outletLocationType = outletLocationType;
    }

    public Double getSales() {
        return sales;
    }

    public void setSales(Double sales) {
        this.sales = sales;
    }
}
