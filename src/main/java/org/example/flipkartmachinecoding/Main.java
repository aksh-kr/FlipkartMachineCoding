package org.example.flipkartmachinecoding;

import org.example.flipkartmachinecoding.services.BookingService;
import org.example.flipkartmachinecoding.services.ProductService;
import org.example.flipkartmachinecoding.services.SellerService;
import org.example.flipkartmachinecoding.services.WarehouseService;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SellerService sellerService = new SellerService();
        ProductService productService = new ProductService(sellerService);
        WarehouseService warehouseService = new WarehouseService();
        BookingService bookingService = new BookingService(sellerService, warehouseService);

        // Sample test cases
        sellerService.registerSeller("sameer.maniyar");
        sellerService.registerSeller("ravi.sharma");
        warehouseService.addWarehouse("blr_wh");
        warehouseService.addWarehouse("delhi_wh");
        warehouseService.addCapacity("blr_wh", "2024-06-01", 100);
        warehouseService.addCapacity("blr_wh", "2024-06-02", 170);
        warehouseService.addCapacity("blr_wh", "2024-06-03", 80);
        warehouseService.addCapacity("delhi_wh", "2024-06-03", 300);

        System.out.println(bookingService.getWarehouseCapacity("delhi_wh", "2024-06-01"));

        productService.addProduct("PID1", "Maggie", "sameer.maniyar");
        productService.addProduct("PID2", "One Plus 8", "sameer.maniyar");
        productService.addProduct("PID3", "Iphone11", "sameer.maniyar");
        productService.addProduct("PID4", "LG AC", "ravi.sharma");

        sellerService.getSeller("sameer.maniyar").getOnboardedWarehouses().add("blr_wh");

        List<Item> items = Arrays.asList(new Item("PID1", 50), new Item("PID2", 110));
        System.out.println(bookingService.getSlots("sameer.maniyar", items, "2024-06-01"));

        sellerService.getSeller("sameer.maniyar").getOnboardedWarehouses().add("delhi_wh");
        System.out.println(bookingService.getSlots("sameer.maniyar", items, "2024-06-01"));

        System.out.println(bookingService.reserveSlot("sameer.maniyar", items, "2024-06-03", "delhi_wh"));
        System.out.println("Success. Amount = " + (160 * 10 - (10 * 1)));

        System.out.println(bookingService.getSellerDetails("sameer.maniyar"));

        System.out.println(bookingService.getWarehouseCapacity("delhi_wh", "2024-06-01"));
    }
}
