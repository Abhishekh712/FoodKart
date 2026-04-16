package com.example.foodkart.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.example.foodkart.CartRepository;
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
        double total = CartRepository.getInstance().getTotalCartPrice();
        totalText.setText(String.format(Locale.US, "₹%.2f", total));

        view.findViewById(R.id.btnCheckout).setOnClickListener(v -> {
            showQrPaymentDialog();
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
        
        // Optional: Ensure the dialog background is transparent to respect rounded corners
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    private void completeOrder() {
        Toast.makeText(getContext(), "Order Placed Successfully!", Toast.LENGTH_SHORT).show();
        CartRepository.getInstance().clearCart();
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
