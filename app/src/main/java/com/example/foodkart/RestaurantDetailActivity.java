package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RestaurantDetailActivity extends AppCompatActivity {

    public static final String EXTRA_RESTAURANT_ID = "restaurant_id";
    private View viewCartBar;
    private TextView cartSummaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        String restaurantId = getIntent().getStringExtra(EXTRA_RESTAURANT_ID);
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant == null) {
            finish();
            return;
        }

        setupToolbar(restaurant.getName());
        setupHeader(restaurant);
        setupMenu(restaurant);
        setupCartBar();
    }

    private void setupToolbar(String name) {
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupHeader(Restaurant r) {
        TextView name = findViewById(R.id.tvDetailName);
        TextView cuisine = findViewById(R.id.tvDetailCuisine);
        TextView rating = findViewById(R.id.tvDetailRating);
        TextView time = findViewById(R.id.tvDetailTime);
        ImageView image = findViewById(R.id.ivDetailImage);

        name.setText(r.getName());
        cuisine.setText(r.getCuisine());
        rating.setText(String.format(Locale.US, "%.1f ★", r.getAverageRating()));
        time.setText(String.format(Locale.US, "%d mins", r.getDeliveryTimeMin()));

        Glide.with(this)
            .load(r.getImageUrl())
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(image);
    }

    private void setupMenu(Restaurant r) {
        RecyclerView rv = findViewById(R.id.rvMenu);
        rv.setLayoutManager(new LinearLayoutManager(this));
        
        List<FoodItem> menu = r.getMenu();
        if (menu.isEmpty()) {
            menu = getMockMenu(r.getName());
        }
        
        rv.setAdapter(new MenuAdapter(menu));
    }

    private void setupCartBar() {
        viewCartBar = findViewById(R.id.viewCartBar);
        cartSummaryText = findViewById(R.id.cartSummaryText);

        CartRepository.getInstance().getCartItems().observe(this, items -> {
            int count = CartRepository.getInstance().getTotalItemCount();
            if (count > 0) {
                viewCartBar.setVisibility(View.VISIBLE);
                double total = CartRepository.getInstance().getTotalCartPrice();
                cartSummaryText.setText(String.format(Locale.US, "%d items | ₹%.2f", count, total));
            } else {
                viewCartBar.setVisibility(View.GONE);
            }
        });

        viewCartBar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }

    private Restaurant findRestaurantById(String id) {
        for (Restaurant r : getMockData()) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }

    private List<FoodItem> getMockMenu(String restaurantName) {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("f1", "Classic " + restaurantName + " Special", 299.0, "Our signature dish made with fresh ingredients and authentic spices.", "https://images.unsplash.com/photo-1546069901-ba9599a7e63c", true));
        menu.add(new FoodItem("f2", "Spicy Gourmet Platter", 450.0, "A delightful mix of spicy flavors that will tingle your taste buds.", "https://images.unsplash.com/photo-1567620905732-2d1ec7bb7445", false));
        menu.add(new FoodItem("f3", "Healthy Garden Salad", 199.0, "Fresh greens, cherry tomatoes, and cucumber with a light balsamic dressing.", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd", true));
        menu.add(new FoodItem("f4", "Crispy Delights", 250.0, "Deep-fried golden perfection served with a tangy dip.", "https://images.unsplash.com/photo-1562967914-608f82629710", true));
        return menu;
    }

    private List<Restaurant> getMockData() {
        List<Restaurant> list = new ArrayList<>();
        list.add(new Restaurant("r1", "Domino's", 550.0, 4.2, 120, 0.45, 1.2, new ArrayList<>(), 
            "android.resource://com.example.foodkart/drawable/dominos_logo", 
            "Pizzas, Italian", 25));
        list.add(new Restaurant("r2", "KFC", 450.0, 3.8, 80, 0.6, 2.5, new ArrayList<>(), 
            "android.resource://com.example.foodkart/drawable/kfc_logo", 
            "Burgers, Fast Food", 35));
        list.add(new Restaurant("r3", "Burger King", 350.0, 4.0, 200, 0.3, 0.8, new ArrayList<>(), 
            "android.resource://com.example.foodkart/drawable/burger_king_logo", 
            "Burgers, American", 20));
        list.add(new Restaurant("r4", "Pizza Hut", 600.0, 4.5, 150, 0.2, 3.0, new ArrayList<>(), 
            "android.resource://com.example.foodkart/drawable/pizza_hut_logo", 
            "Pizzas, Continental", 40));
        list.add(new Restaurant("r5", "Subway", 300.0, 4.1, 90, 0.5, 1.5, new ArrayList<>(), 
            "android.resource://com.example.foodkart/drawable/subway_logo",
            "Salads, Healthy Food", 15));
        return list;
    }
}
