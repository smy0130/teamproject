package model;

import java.util.ArrayList;

public class Order {
    private ArrayList<CartItem> items;

    public Order(ArrayList<CartItem> items) {
        this.items = items;
    }
   
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
