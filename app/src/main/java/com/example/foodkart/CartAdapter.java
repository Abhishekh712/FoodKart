package com.example.foodkart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<CartItem> items;

    public CartAdapter(List<CartItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = items.get(position);
        FoodItem food = item.getFoodItem();

        holder.name.setText(food.getName());
        holder.price.setText(String.format(Locale.US, "₹%.0f", food.getPrice()));
        holder.quantity.setText(String.valueOf(item.getQuantity()));

        Glide.with(holder.image.getContext())
            .load(food.getImageUrl())
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.image);

        holder.btnPlus.setOnClickListener(v -> {
            CartRepository.getInstance().addItem(food);
            notifyItemChanged(position);
        });

        holder.btnMinus.setOnClickListener(v -> {
            CartRepository.getInstance().removeItem(food);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, quantity, btnMinus, btnPlus;

        ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.ivCartFoodImage);
            name = v.findViewById(R.id.tvCartFoodName);
            price = v.findViewById(R.id.tvCartFoodPrice);
            quantity = v.findViewById(R.id.tvCartQuantity);
            btnMinus = v.findViewById(R.id.btnMinus);
            btnPlus = v.findViewById(R.id.btnPlus);
        }
    }
}