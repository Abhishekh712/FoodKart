package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_pizza);

        Button p1 = findViewById(R.id.pizza1);
        Button p2 = findViewById(R.id.pizza2);

        p1.setOnClickListener(v -> addToCart("Margherita"));
        p2.setOnClickListener(v -> addToCart("Farmhouse"));
    }

    void addToCart(String item) {
        Intent i = new Intent(this, CartActivity.class);
        i.putExtra("item", item);
        startActivity(i);
    }
}