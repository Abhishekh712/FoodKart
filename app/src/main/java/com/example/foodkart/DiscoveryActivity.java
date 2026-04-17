package com.example.foodkart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodkart.ui.CheckoutBottomSheet;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiscoveryActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "FoodKartPrefs";
    private static final String KEY_LOGGED_IN_USER = "logged_in_user";
    
    // User-specific keys
    private String keyPricePref;
    private String keyQualityPref;
    private String keyDistancePref;

    private RecommendationViewModel viewModel;
    private HomeAdapter adapter;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private TextView weightIndicator;
    private View viewCartBar;
    private TextView cartSummaryText;
    private String activeFilterType;
    private SharedPreferences sharedPrefs;
    private String currentUser = "guest";
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_discovery);

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        currentUser = sharedPrefs.getString(KEY_LOGGED_IN_USER, "guest");
        dbHelper = new DatabaseHelper(this);
        
        // Initialize user-specific preference keys
        keyPricePref = currentUser + "_price_pref";
        keyQualityPref = currentUser + "_quality_pref";
        keyDistancePref = currentUser + "_distance_pref";

        activeFilterType = getIntent().getStringExtra(MainActivity.EXTRA_FILTER_TYPE);
        viewModel = new ViewModelProvider(this).get(RecommendationViewModel.class);
        
        initUI();
        setupObservers();
        setupCartBar();
        
        // Fetch data from SQL Database
        List<Restaurant> initialData = dbHelper.getAllRestaurants();
        
        // Apply "Best Value" filtering logic
        if (MainActivity.FILTER_BEST_VALUE.equals(activeFilterType)) {
            initialData = filterRestaurantsByBestValue(initialData);
        }
        
        viewModel.initData(initialData, 500.0);
        
        // Load persisted preferences for the current user
        loadAndApplyPreferences();
    }

    private void loadAndApplyPreferences() {
        float p = sharedPrefs.getFloat(keyPricePref, 50f);
        float q = sharedPrefs.getFloat(keyQualityPref, 50f);
        float d = sharedPrefs.getFloat(keyDistancePref, 50f);

        Slider pSlider = findViewById(R.id.priceSlider);
        Slider qSlider = findViewById(R.id.qualitySlider);
        Slider dSlider = findViewById(R.id.distanceSlider);

        pSlider.setValue(p);
        qSlider.setValue(q);
        dSlider.setValue(d);

        double targetBudget = 1500.0 - (p * 14.0);
        viewModel.updateRanking(p, q, d, targetBudget);
    }

    private List<Restaurant> filterRestaurantsByBestValue(List<Restaurant> originalList) {
        List<Restaurant> filtered = new ArrayList<>();
        for (Restaurant r : originalList) {
            List<FoodItem> cheapItems = new ArrayList<>();
            for (FoodItem item : r.getMenu()) {
                if (item.getPrice() <= 350) {
                    cheapItems.add(item);
                }
            }
            if (!cheapItems.isEmpty()) {
                // Clone restaurant to avoid modifying the original list if needed
                Restaurant cloned = new Restaurant(r.getId(), r.getName(), r.getPriceInINR(), r.getAverageRating(), r.getReviewCount(), r.getRatingVariance(), r.getDistanceInKm(), r.getUserInteractions(), r.getImageUrl(), r.getCuisine(), r.getDeliveryTimeMin());
                cloned.setMenu(cheapItems);
                filtered.add(cloned);
            }
        }
        return filtered;
    }

    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.homeRecyclerView);
        adapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        View bottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        
        ExtendedFloatingActionButton btnTune = findViewById(R.id.btnTune);
        btnTune.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        weightIndicator = findViewById(R.id.weightIndicator);

        Slider pSlider = findViewById(R.id.priceSlider);
        Slider qSlider = findViewById(R.id.qualitySlider);
        Slider dSlider = findViewById(R.id.distanceSlider);

        // Map 0-100 to meaningful values via tooltips
        pSlider.setLabelFormatter(value -> {
            int price = (int) (1500 - (value * 14));
            return "Target: ₹" + price;
        });

        dSlider.setLabelFormatter(value -> {
            float distance = 8.0f - (value * 0.075f);
            return String.format(Locale.US, "Within %.1f km", distance);
        });

        qSlider.setLabelFormatter(value -> {
            float rating = 3.0f + (value * 0.02f);
            return String.format(Locale.US, "Min %.1f ★", rating);
        });

        Slider.OnChangeListener listener = (slider, value, fromUser) -> {
            if (fromUser) {
                // Fix: Ensure values are at least 1 to avoid division by zero or log errors in AHP
                float p = Math.max(1, pSlider.getValue());
                float q = Math.max(1, qSlider.getValue());
                float d = Math.max(1, dSlider.getValue());
                
                // Persist preferences with user-specific keys
                sharedPrefs.edit()
                    .putFloat(keyPricePref, p)
                    .putFloat(keyQualityPref, q)
                    .putFloat(keyDistancePref, d)
                    .apply();

                // Calculate the target budget based on the same formula used in the LabelFormatter
                double targetBudget = 1500.0 - (p * 14.0);
                
                viewModel.updateRanking(p, q, d, targetBudget);
            }
        };

        pSlider.addOnChangeListener(listener);
        qSlider.addOnChangeListener(listener);
        dSlider.addOnChangeListener(listener);
    }

    private void setupObservers() {
        viewModel.getRankedRestaurants().observe(this, restaurants -> {
            List<HomeItem> items = new ArrayList<>();
            items.add(new HomeItem(HomeItem.TYPE_SEARCH));
            
            // Filter restaurants under 350 for the horizontal banner section using DB data
            List<Restaurant> allFromDb = dbHelper.getAllRestaurants();
            List<Restaurant> under350 = new ArrayList<>();
            for (Restaurant r : allFromDb) {
                if (r.getPriceInINR() <= 350) { 
                    under350.add(r);
                }
            }
            items.add(new HomeItem(under350));
            
            String header = MainActivity.FILTER_BEST_VALUE.equals(activeFilterType) ? "Best Value (Under ₹350)" : "Recommended for you";
            items.add(new HomeItem(HomeItem.TYPE_SECTION_HEADER, header));
            
            // Pass the filter type to the adapter items so it can be passed to DetailActivity
            for (Restaurant r : restaurants) {
                HomeItem restaurantItem = new HomeItem(r);
                restaurantItem.setFilterType(activeFilterType);
                items.add(restaurantItem);
            }
            
            items.add(new HomeItem(HomeItem.TYPE_SECTION_HEADER, "Explore restaurants"));
            for (Restaurant r : restaurants) {
                HomeItem restaurantItem = new HomeItem(r);
                restaurantItem.setFilterType(activeFilterType);
                items.add(restaurantItem);
            }

            adapter.submitList(items);
        });

        viewModel.getCurrentWeights().observe(this, ahp -> {
            weightIndicator.setText(String.format(Locale.US, 
                "Debug: Weights [%.2f, %.2f, %.2f] CR: %.3f", 
                ahp.weights[0], ahp.weights[1], ahp.weights[2], ahp.consistencyRatio));
        });
    }

    private void setupCartBar() {
        viewCartBar = findViewById(R.id.viewCartBar);
        cartSummaryText = findViewById(R.id.cartSummaryText);

        CartRepository.getInstance(this).getCartItems().observe(this, items -> {
            int count = CartRepository.getInstance(this).getTotalItemCount();
            if (count > 0) {
                viewCartBar.setVisibility(View.VISIBLE);
                double total = CartRepository.getInstance(this).getTotalCartPrice();
                cartSummaryText.setText(String.format(Locale.US, "%d items | ₹%.2f", count, total));
            } else {
                viewCartBar.setVisibility(View.GONE);
            }
            adapter.notifyDataSetChanged();
        });

        viewCartBar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }
}
