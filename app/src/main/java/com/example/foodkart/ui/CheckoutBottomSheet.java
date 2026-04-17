package com.example.foodkart.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.example.foodkart.CartActivity;
import com.example.foodkart.CartRepository;
import com.example.foodkart.MainActivity;
import com.example.foodkart.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Locale;

public class CheckoutBottomSheet extends BottomSheetDialogFragment {

    private CountDownTimer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView totalText = view.findViewById(R.id.totalText);
        double total = CartRepository.getInstance(requireContext()).getTotalCartPrice();
        totalText.setText(String.format(Locale.US, "₹%.2f", total));

        // Original QR Payment option
        view.findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            showQrPaymentDialog();
        });

        // New Razorpay Payment option - Fixed logic
        view.findViewById(R.id.btnRazorpay).setOnClickListener(v -> {
            Log.d("RAZORPAY_DEBUG", "Starting payment from checkout");
            
            if (getActivity() instanceof CartActivity) {
                ((CartActivity) getActivity()).startPayment(total);
                dismiss();
            } else if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).startPayment(total);
                dismiss();
            } else {
                // Fallback for safety
                Toast.makeText(getContext(), "Payment gateway is loading...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showQrPaymentDialog() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_qr_payment, null);
        AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.CustomDialogTheme)
                .setView(dialogView)
                .setCancelable(false)
                .create();

        TextView tvTimer = dialogView.findViewById(R.id.tvTimer);
        
        // Timer for 2 minutes (120,000 milliseconds)
        timer = new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                long minutes = (millisUntilFinished / 1000) / 60;
                long seconds = (millisUntilFinished / 1000) % 60;
                tvTimer.setText(String.format(Locale.US, "%02d:%02d", minutes, seconds));
            }

            public void onFinish() {
                dialog.dismiss();
                Toast.makeText(getContext(), "Payment Session Expired", Toast.LENGTH_LONG).show();
            }
        }.start();

        dialogView.findViewById(R.id.btnPaid).setOnClickListener(v -> {
            if (timer != null) timer.cancel();
            dialog.dismiss();
            completeOrder();
        });

        dialogView.findViewById(R.id.btnCancelPayment).setOnClickListener(v -> {
            if (timer != null) timer.cancel();
            dialog.dismiss();
        });

        dialog.show();
        
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    private void completeOrder() {
        Toast.makeText(getContext(), "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
        // Clear cart and redirect home
        CartRepository.getInstance(requireContext()).placeOrder();
        
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (timer != null) {
            timer.cancel();
        }
    }
}
