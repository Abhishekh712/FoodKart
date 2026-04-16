package com.example.foodkart;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.example.foodkart.ui.CheckoutBottomSheet;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {

    private CartAdapter adapter;
    private RecyclerView rvCartItems;
    private TextView tvTotalAmount;
    private TextView tvItemTotal;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_cart);

        setupToolbar();
        setupRecyclerView();
        setupObservers();

        findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            new CheckoutBottomSheet().show(getSupportFragmentManager(), "CheckoutSheet");
        });
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Checkout");
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void setupRecyclerView() {
        rvCartItems = findViewById(R.id.rvCartItems);
        rvCartItems.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(new java.util.ArrayList<>());
        rvCartItems.setAdapter(adapter);
    }

    private void setupObservers() {
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        tvItemTotal = findViewById(R.id.tvItemTotal);

        CartRepository.getInstance().getCartItems().observe(this, items -> {
            if (items.isEmpty()) {
                finish(); 
            } else {
                adapter = new CartAdapter(items);
                rvCartItems.setAdapter(adapter);
                
                double total = CartRepository.getInstance().getTotalCartPrice();
                String totalStr = String.format(Locale.US, "₹%.2f", total);
                tvTotalAmount.setText(totalStr);
                if (tvItemTotal != null) {
                    tvItemTotal.setText(totalStr);
                }
            }
        });
    }
}
