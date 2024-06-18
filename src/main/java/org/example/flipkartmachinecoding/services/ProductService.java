package org.example.flipkartmachinecoding.services;


import org.example.flipkartmachinecoding.models.Product;
import org.example.flipkartmachinecoding.models.Seller;

public class ProductService {
    private SellerService sellerService;

    public ProductService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void addProduct(String productId, String productName, String sellerId) {
        Seller seller = sellerService.getSeller(sellerId);
        if (seller != null) {
            Product product = new Product(productId, productName, sellerId);
            seller.getProducts().put(productId, product);
        }
    }
}

