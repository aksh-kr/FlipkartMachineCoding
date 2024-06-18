package org.example.flipkartmachinecoding.models;
public class Product {
    private String productId;
    private String productName;
    private String sellerId;

    public Product(String productId, String productName, String sellerId) {
        this.productId = productId;
        this.productName = productName;
        this.sellerId = sellerId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getSellerId() {
        return sellerId;
    }
}

