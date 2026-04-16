package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BurgerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_burger);

        Button b1 = findViewById(R.id.b1);

        b1.setOnClickListener(v -> {
            Intent i = new Intent(this, CartActivity.class);
            i.putExtra("item", "Veg Burger");
            startActivity(i);
        });
    }
}