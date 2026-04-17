package com.example.foodkart;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static CartRepository instance;
    private final DatabaseHelper dbHelper;
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());
    private String currentUserEmail = "guest";

    private CartRepository(Context context) {
        dbHelper = new DatabaseHelper(context.getApplicationContext());
        // Load initial user session
        currentUserEmail = context.getSharedPreferences("FoodKartPrefs", Context.MODE_PRIVATE)
                .getString("logged_in_user", "guest");
        refreshCart();
    }

    public static synchronized CartRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CartRepository(context);
        }
        return instance;
    }

    // Overloaded for when context is not available but instance exists
    public static synchronized CartRepository getInstance() {
        return instance;
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public void refreshUserSession(Context context) {
        currentUserEmail = context.getSharedPreferences("FoodKartPrefs", Context.MODE_PRIVATE)
                .getString("logged_in_user", "guest");
        refreshCart();
    }

    public void refreshCart() {
        List<CartItem> items = dbHelper.getCartItems(currentUserEmail);
        cartItems.postValue(items);
    }

    public void addItem(FoodItem foodItem) {
        dbHelper.addToCart(currentUserEmail, foodItem);
        refreshCart();
    }

    public void removeItem(FoodItem foodItem) {
        dbHelper.removeFromCart(currentUserEmail, foodItem.getId());
        refreshCart();
    }

    public void clearCart() {
        dbHelper.clearCart(currentUserEmail);
        refreshCart();
    }

    public void placeOrder() {
        double total = getTotalCartPrice();
        if (total > 0) {
            dbHelper.addOrder(currentUserEmail, total);
            clearCart();
        }
    }

    public double getTotalCartPrice() {
        List<CartItem> items = cartItems.getValue();
        double total = 0;
        if (items != null) {
            for (CartItem item : items) {
                total += item.getTotalPrice();
            }
        }
        return total;
    }

    public int getTotalItemCount() {
        List<CartItem> items = cartItems.getValue();
        int count = 0;
        if (items != null) {
            for (CartItem item : items) {
                count += item.getQuantity();
            }
        }
        return count;
    }
}
