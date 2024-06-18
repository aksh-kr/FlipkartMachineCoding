package org.example.flipkartmachinecoding.services;

import org.example.flipkartmachinecoding.models.Seller;

import java.util.HashMap;
import java.util.Map;

public class SellerService {
    private Map<String, Seller> sellers;

    public SellerService() {
        this.sellers = new HashMap<>();
    }

    public void registerSeller(String sellerId) {
        if (!sellers.containsKey(sellerId)) {
            sellers.put(sellerId, new Seller(sellerId));
        }
    }

    public Seller getSeller(String sellerId) {
        return sellers.get(sellerId);
    }
}
