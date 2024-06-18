package org.example.flipkartmachinecoding.models;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private String warehouseId;
    private Map<String, Integer> capacity;

    public Warehouse(String warehouseId) {
        this.warehouseId = warehouseId;
        this.capacity = new HashMap<>();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public Map<String, Integer> getCapacity() {
        return capacity;
    }

    public void setCapacity(String date, int capacity) {
        this.capacity.put(date, capacity);
    }
}
