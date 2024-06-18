package org.example.flipkartmachinecoding.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class Seller {
    private String sellerId;
    private int rating;
    private Map<String, Product> products;
    private Set<String> onboardedWarehouses;
    private Map<String, Integer> warehouseInventoryCount;

    public Seller(String sellerId) {
        this.sellerId = sellerId;
        this.rating = 1;
        this.products = new HashMap<>();
        this.onboardedWarehouses = new HashSet<>();
        this.warehouseInventoryCount = new HashMap<>();
    }

    public String getSellerId() {
        return sellerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public Set<String> getOnboardedWarehouses() {
        return onboardedWarehouses;
    }

    public Map<String, Integer> getWarehouseInventoryCount() {
        return warehouseInventoryCount;
    }
}
