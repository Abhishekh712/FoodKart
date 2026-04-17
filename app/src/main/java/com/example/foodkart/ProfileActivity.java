package com.example.foodkart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "FoodKartPrefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_LOGGED_IN_USER = "logged_in_user";

    private EditText etName, etEmail, etPhone, etAddress;
    private TextInputLayout tilName, tilEmail, tilPhone, tilAddress;
    private MaterialButton btnSave, btnLogout;
    private SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        initViews();
        setupListeners();
        loadUserData();
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhone = findViewById(R.id.tilPhone);
        tilAddress = findViewById(R.id.tilAddress);

        btnSave = findViewById(R.id.btnSaveProfile);
        btnLogout = findViewById(R.id.btnLogout);

        findViewById(R.id.toolbar).setOnClickListener(v -> finish());
    }

    private void setupListeners() {
        btnSave.setOnClickListener(v -> {
            if (validateForm()) {
                updateProfile();
            }
        });

        btnLogout.setOnClickListener(v -> {
            sharedPrefs.edit()
                .putBoolean(KEY_IS_LOGGED_IN, false)
                .putString(KEY_LOGGED_IN_USER, "guest")
                .apply();

            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnUploadPic).setOnClickListener(v -> {
            Toast.makeText(this, "Profile picture upload coming soon!", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.btnChangePassword).setOnClickListener(v -> showChangePasswordDialog());
    }

    private void showChangePasswordDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_change_password, null);
        EditText etOldPass = view.findViewById(R.id.etOldPassword);
        EditText etNewPass = view.findViewById(R.id.etNewPassword);
        EditText etConfirmPass = view.findViewById(R.id.etConfirmPassword);
        MaterialButton btnUpdate = view.findViewById(R.id.btnUpdatePassword);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();

        btnUpdate.setOnClickListener(v -> {
            String oldPass = etOldPass.getText().toString();
            String newPass = etNewPass.getText().toString();
            String confirmPass = etConfirmPass.getText().toString();

            String userEmail = sharedPrefs.getString(KEY_LOGGED_IN_USER, "guest");
            String storedPass = sharedPrefs.getString(userEmail + "_password", "");

            if (oldPass.equals(storedPass)) {
                if (!newPass.isEmpty() && newPass.equals(confirmPass)) {
                    sharedPrefs.edit().putString(userEmail + "_password", newPass).apply();
                    Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(this, "New passwords do not match or are empty", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Incorrect old password", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void loadUserData() {
        String userEmail = sharedPrefs.getString(KEY_LOGGED_IN_USER, "guest");
        String userName = sharedPrefs.getString(userEmail + "_name", "Guest User");
        
        etName.setText(userName);
        etEmail.setText(userEmail.equals("guest") ? "guest@foodkart.com" : userEmail);
        
        etPhone.setText(sharedPrefs.getString(userEmail + "_phone", "8181234567"));
        etAddress.setText(sharedPrefs.getString(userEmail + "_address", "123, Luxury Street, Foodie City, PIN: 560001"));
    }

    private boolean validateForm() {
        boolean isValid = true;
        if (TextUtils.isEmpty(etName.getText())) {
            tilName.setError("Name is required");
            isValid = false;
        } else {
            tilName.setError(null);
        }

        String email = etEmail.getText().toString();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Enter a valid email address");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }

        String phone = etPhone.getText().toString();
        if (phone.length() != 10) {
            tilPhone.setError("Phone number must be 10 digits");
            isValid = false;
        } else {
            tilPhone.setError(null);
        }

        if (TextUtils.isEmpty(etAddress.getText())) {
            tilAddress.setError("Address is required for delivery");
            isValid = false;
        } else {
            tilAddress.setError(null);
        }

        return isValid;
    }

    private void updateProfile() {
        String userEmail = sharedPrefs.getString(KEY_LOGGED_IN_USER, "guest");
        sharedPrefs.edit()
            .putString(userEmail + "_name", etName.getText().toString().trim())
            .putString(userEmail + "_phone", etPhone.getText().toString().trim())
            .putString(userEmail + "_address", etAddress.getText().toString().trim())
            .apply();

        Toast.makeText(this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
