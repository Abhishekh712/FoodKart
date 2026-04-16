package com.example.foodkart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartRepository {
    private static CartRepository instance;
    private final Map<String, CartItem> cartMap = new HashMap<>();
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());

    private CartRepository() {}

    public static synchronized CartRepository getInstance() {
        if (instance == null) {
            instance = new CartRepository();
        }
        return instance;
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public synchronized void addItem(FoodItem foodItem) {
        if (cartMap.containsKey(foodItem.getId())) {
            CartItem item = cartMap.get(foodItem.getId());
            item.setQuantity(item.getQuantity() + 1);
        } else {
            cartMap.put(foodItem.getId(), new CartItem(foodItem, 1));
        }
        notifyChanges();
    }

    public synchronized void removeItem(FoodItem foodItem) {
        if (cartMap.containsKey(foodItem.getId())) {
            CartItem item = cartMap.get(foodItem.getId());
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                cartMap.remove(foodItem.getId());
            }
            notifyChanges();
        }
    }

    public synchronized void clearCart() {
        cartMap.clear();
        notifyChanges();
    }

    public double getTotalCartPrice() {
        double total = 0;
        for (CartItem item : cartMap.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public int getTotalItemCount() {
        int count = 0;
        for (CartItem item : cartMap.values()) {
            count += item.getQuantity();
        }
        return count;
    }

    private void notifyChanges() {
        cartItems.postValue(new ArrayList<>(cartMap.values()));
    }
}