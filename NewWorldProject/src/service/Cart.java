package service;

import model.CartItem;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }
}