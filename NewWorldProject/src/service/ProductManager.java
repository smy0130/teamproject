package service;

import model.Product;

import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
