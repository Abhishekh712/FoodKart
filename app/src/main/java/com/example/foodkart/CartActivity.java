package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import com.example.foodkart.ui.CheckoutBottomSheet;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;
import java.util.Locale;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    private CartAdapter adapter;
    private RecyclerView rvCartItems;
    private TextView tvTotalAmount;
    private TextView tvItemTotal;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_cart);

        // Preload Razorpay
        Checkout.preload(getApplicationContext());

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

    /**
     * Starts the Razorpay payment flow.
     */
    public void startPayment(double amount) {
        final Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_SeSHp76cDY5J3k");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "FoodKart");
            options.put("description", "Food Order Payment");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color", "#E53935");
            options.put("currency", "INR");
            options.put("amount", (int)(amount * 100)); // Amount in paise

            JSONObject prefill = new JSONObject();
            prefill.put("email", "customer@example.com");
            prefill.put("contact", "9876543210");
            options.put("prefill", prefill);

            checkout.open(this, options);
        } catch (Exception e) {
            Toast.makeText(this, "Error in starting Razorpay: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        // Trigger SQL order placement logic
        CartRepository.getInstance(this).placeOrder();

        // Navigate to Order Success flow (returning home)
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
    }
}
