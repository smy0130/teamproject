package model;

public class Product {
    private String name;
    private int price;
    private int stock; // 새로운 재고 필드

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= stock) {
            stock -= quantity;
        } else {
            System.out.println("재고가 부족합니다");
        }
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}