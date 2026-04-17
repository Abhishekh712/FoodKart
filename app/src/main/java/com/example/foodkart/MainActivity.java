package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    public static final String EXTRA_FILTER_TYPE = "filter_type";
    public static final String FILTER_BEST_VALUE = "best_value";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_home);

        // Preload Razorpay to speed up checkout later
        Checkout.preload(getApplicationContext());

        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        setupIntentCards();
    }

    private void setupIntentCards() {
        findViewById(R.id.cardBestValue).setOnClickListener(v -> {
            Intent intent = new Intent(this, DiscoveryActivity.class);
            intent.putExtra(EXTRA_FILTER_TYPE, FILTER_BEST_VALUE);
            startActivity(intent);
        });

        findViewById(R.id.cardTopRated).setOnClickListener(v -> {
            startActivity(new Intent(this, DiscoveryActivity.class));
        });

        findViewById(R.id.cardQuick).setOnClickListener(v -> {
            startActivity(new Intent(this, DiscoveryActivity.class));
        });
    }

    public void onExploreClicked(View v) {
        startActivity(new Intent(this, DiscoveryActivity.class));
    }

    /**
     * Starts the Razorpay payment flow.
     * Called from CheckoutBottomSheet via activity context casting.
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
        
        // Navigate to Order Success flow (returning home for now as requested)
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment Failed: " + response, Toast.LENGTH_SHORT).show();
    }
}
