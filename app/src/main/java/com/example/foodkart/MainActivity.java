package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_home);

        setupIntentCards();
    }

    private void setupIntentCards() {
        // Intent Card: Best Value
        findViewById(R.id.cardBestValue).setOnClickListener(v -> {
            Intent intent = new Intent(this, DiscoveryActivity.class);
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