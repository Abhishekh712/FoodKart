package com.example.foodkart;

import android.content.Intent;
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
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiscoveryActivity extends AppCompatActivity {

    private RecommendationViewModel viewModel;
    private HomeAdapter adapter;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private TextView liveFeedback;
    private TextView weightIndicator;
    private View viewCartBar;
    private TextView cartSummaryText;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_discovery);

        viewModel = new ViewModelProvider(this).get(RecommendationViewModel.class);
        
        initUI();
        setupObservers();
        setupCartBar();
        
        // Load initial data
        List<Restaurant> initialData = getMockData();
        viewModel.initData(initialData, 500.0);
    }

    private void initUI() {
        RecyclerView recyclerView = findViewById(R.id.homeRecyclerView);
        adapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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

        liveFeedback = findViewById(R.id.liveFeedback);
        weightIndicator = findViewById(R.id.weightIndicator);

        Slider pSlider = findViewById(R.id.priceSlider);
        Slider qSlider = findViewById(R.id.qualitySlider);
        Slider dSlider = findViewById(R.id.distanceSlider);

        Slider.OnChangeListener listener = (slider, value, fromUser) -> {
            if (fromUser) {
                viewModel.updateRanking(pSlider.getValue(), qSlider.getValue(), dSlider.getValue());
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
            
            // Filter restaurants under 300 for the banner section
            List<Restaurant> under300 = new ArrayList<>();
            for (Restaurant r : getMockData()) {
                if (r.getPriceInINR() <= 350) { // Using 350 to have some variety in mock
                    under300.add(r);
                }
            }
            items.add(new HomeItem(under300));
            
            items.add(new HomeItem(HomeItem.TYPE_SECTION_HEADER, "Recommended for you"));
            for (Restaurant r : restaurants) {
                items.add(new HomeItem(r));
            }
            
            items.add(new HomeItem(HomeItem.TYPE_SECTION_HEADER, "Explore restaurants"));
            for (Restaurant r : restaurants) {
                items.add(new HomeItem(r));
            }

            adapter.submitList(items);
            updateLiveFeedback(restaurants);
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

        CartRepository.getInstance().getCartItems().observe(this, items -> {
            int count = CartRepository.getInstance().getTotalItemCount();
            if (count > 0) {
                viewCartBar.setVisibility(View.VISIBLE);
                double total = CartRepository.getInstance().getTotalCartPrice();
                cartSummaryText.setText(String.format(Locale.US, "%d items | ₹%.2f", count, total));
            } else {
                viewCartBar.setVisibility(View.GONE);
            }
            // Update adapter to reflect quantity changes
            adapter.notifyDataSetChanged();
        });

        viewCartBar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void updateLiveFeedback(List<Restaurant> list) {
        if (!list.isEmpty()) {
            liveFeedback.setText(String.format(Locale.US, "Found %d matches for your preference", list.size()));
        }
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
