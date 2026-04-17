package com.example.foodkart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "FoodKartPrefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_LOGGED_IN_USER = "logged_in_user";

    private EditText etName, etEmail, etPassword;
    private SharedPreferences sharedPrefs;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnSignUp).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString();

            if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
                // Save user data to SQLite database
                boolean success = dbHelper.addUser(name, email, pass);
                
                if (success) {
                    // Still keep track of session in SharedPreferences
                    sharedPrefs.edit()
                        .putBoolean(KEY_IS_LOGGED_IN, true)
                        .putString(KEY_LOGGED_IN_USER, email)
                        .apply();

                    Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed. Email might already exist.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.tvLoginLink).setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }
}
