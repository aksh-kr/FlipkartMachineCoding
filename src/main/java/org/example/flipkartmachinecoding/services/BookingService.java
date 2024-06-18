package org.example.flipkartmachinecoding.services;

import org.example.flipkartmachinecoding.exceptions.CapacityNotAvailableException;
import org.example.flipkartmachinecoding.exceptions.SellerNotFoundException;
import org.example.flipkartmachinecoding.exceptions.WarehouseNotFoundException;
import org.example.flipkartmachinecoding.models.Seller;
import org.example.flipkartmachinecoding.models.Warehouse;
import org.example.flipkartmachinecoding.utils.DateUtil;

import java.util.*;

public class BookingService {
    private SellerService sellerService;
    private WarehouseService warehouseService;

    public BookingService(SellerService sellerService, WarehouseService warehouseService) {
        this.sellerService = sellerService;
        this.warehouseService = warehouseService;
    }

    public List<String> getWarehouseCapacity(String warehouseId, String startDate) {
        Warehouse warehouse = warehouseService.getWarehouse(warehouseId);
        if (warehouse == null) {
            throw new WarehouseNotFoundException("Warehouse not found.");
        }

        List<String> capacityInfo = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String date = DateUtil.addDays(startDate, i);
            int capacity = warehouse.getCapacity().getOrDefault(date, 0);
            capacityInfo.add(date + " - Available capacity: " + capacity);
        }
        return capacityInfo;
    }

    public List<String> getSlots(String sellerId, List<Item> items, String startDate) {
        Seller seller = sellerService.getSeller(sellerId);
        if (seller == null) {
            throw new SellerNotFoundException("Seller not found.");
        }

        int totalQuantity = items.stream().mapToInt(Item::getQuantity).sum();
        List<String> availableSlots = new ArrayList<>();
        for (String warehouseId : seller.getOnboardedWarehouses()) {
            Warehouse warehouse = warehouseService.getWarehouse(warehouseId);
            for (int i = 0; i < 10; i++) {
                String date = DateUtil.addDays(startDate, i);
                int capacity = warehouse.getCapacity().getOrDefault(date, 0);
                if (capacity >= totalQuantity) {
                    availableSlots.add(warehouseId + ", " + date);
                }
            }
        }

        return availableSlots;
    }
// testing git
    public boolean reserveSlot(String sellerId, List<Item> items, String date, String warehouseId) {
        Seller seller = sellerService.getSeller(sellerId);
        if (seller == null) {
            throw new SellerNotFoundException("Seller not found.");
        }

        if (!seller.getOnboardedWarehouses().contains(warehouseId)) {
            return false;
        }

        int totalQuantity = items.stream().mapToInt(Item::getQuantity).sum();
        Warehouse warehouse = warehouseService.getWarehouse(warehouseId);
        int capacity = warehouse.getCapacity().getOrDefault(date, 0);

        if (capacity >= totalQuantity) {
            warehouse.setCapacity(date, capacity - totalQuantity);
            int currentInventory = seller.getWarehouseInventoryCount().getOrDefault(warehouseId, 0);
            seller.getWarehouseInventoryCount().put(warehouseId, currentInventory + totalQuantity);

            // Update seller rating
            int newRating = seller.getRating() + totalQuantity / 150;
            seller.setRating(Math.min(newRating, 10));

            return true;
        }

        throw new CapacityNotAvailableException("Capacity not available.");
    }

    public String getSellerDetails(String sellerId) {
        Seller seller = sellerService.getSeller(sellerId);
        if (seller == null) {
            throw new SellerNotFoundException("Seller not found.");
        }

        StringBuilder details = new StringBuilder();
        details.append("Seller id: ").append(seller.getSellerId()).append("\n");
        details.append("Seller rating: ").append(seller.getRating()).append("\n");
        details.append("Products: ").append(seller.getProducts().keySet()).append("\n");
        details.append("Onboarded warehouses: ").append(seller.getOnboardedWarehouses()).append("\n");

        details.append("Inventory view: ");
        for (Map.Entry<String, Integer> entry : seller.getWarehouseInventoryCount().entrySet()) {
            details.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        return details.toString();
    }
}
