package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_FILTER_TYPE = "filter_type";
    public static final String FILTER_BEST_VALUE = "best_value";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        setupIntentCards();
    }

    private void setupIntentCards() {
        // Intent Card: Best Value
        findViewById(R.id.cardBestValue).setOnClickListener(v -> {
            Intent intent = new Intent(this, DiscoveryActivity.class);
            intent.putExtra(EXTRA_FILTER_TYPE, FILTER_BEST_VALUE);
            startActivity(intent);
        });

        // Intent Card: Top Rated
        findViewById(R.id.cardTopRated).setOnClickListener(v -> {
            startActivity(new Intent(this, DiscoveryActivity.class));
        });

        // Intent Card: Quick Delivery
        findViewById(R.id.cardQuick).setOnClickListener(v -> {
            startActivity(new Intent(this, DiscoveryActivity.class));
        });
    }

    public void onExploreClicked(View v) {
        startActivity(new Intent(this, DiscoveryActivity.class));
    }
}
