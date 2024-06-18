package org.example.flipkartmachinecoding.services;

import org.example.flipkartmachinecoding.models.Warehouse;

import java.util.HashMap;
import java.util.Map;

public class WarehouseService {
    private Map<String, Warehouse> warehouses;

    public WarehouseService() {
        this.warehouses = new HashMap<>();
    }

    public void addWarehouse(String warehouseId) {
        if (!warehouses.containsKey(warehouseId)) {
            warehouses.put(warehouseId, new Warehouse(warehouseId));
        }
    }

    public void addCapacity(String warehouseId, String date, int capacity) {
        Warehouse warehouse = warehouses.get(warehouseId);
        if (warehouse != null) {
            warehouse.setCapacity(date, capacity);
        }
    }

    public Warehouse getWarehouse(String warehouseId) {
        return warehouses.get(warehouseId);
    }
}
