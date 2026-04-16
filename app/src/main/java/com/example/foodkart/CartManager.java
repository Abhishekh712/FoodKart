package com.example.foodkart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private final MutableLiveData<List<FoodItem>> cartItems = new MutableLiveData<>(new ArrayList<>());

    private CartManager() {}

    public static synchronized CartManager getInstance() {
        if (instance == null) instance = new CartManager();
        return instance;
    }

    public LiveData<List<FoodItem>> getCartItems() {
        return cartItems;
    }

    public void addItem(FoodItem item) {
        List<FoodItem> current = new ArrayList<>(cartItems.getValue());
        current.add(item);
        cartItems.setValue(current);
    }

    public void clearCart() {
        cartItems.setValue(new ArrayList<>());
    }

    public double getTotalPrice() {
        double total = 0;
        for (FoodItem item : cartItems.getValue()) {
            total += item.getPrice();
        }
        return total;
    }
}