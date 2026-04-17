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
    public static final String EXTRA_FILTER_TYPE = "filter_type";
    private View viewCartBar;
    private TextView cartSummaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        String restaurantId = getIntent().getStringExtra(EXTRA_RESTAURANT_ID);
        String filterType = getIntent().getStringExtra(EXTRA_FILTER_TYPE);
        
        Restaurant restaurant = findRestaurantById(restaurantId);

        if (restaurant == null) {
            finish();
            return;
        }

        // Apply filtering logic to the menu if "Best Value" was selected
        if (MainActivity.FILTER_BEST_VALUE.equals(filterType)) {
            List<FoodItem> filteredMenu = new ArrayList<>();
            for (FoodItem item : restaurant.getMenu()) {
                if (item.getPrice() <= 350) {
                    filteredMenu.add(item);
                }
            }
            restaurant.setMenu(filteredMenu);
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
        rv.setAdapter(new MenuAdapter(r.getMenu()));
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
        for (Restaurant r : MockData.getRestaurants()) {
            if (r.getId().equals(id)) return r;
        }
        return null;
    }
}
