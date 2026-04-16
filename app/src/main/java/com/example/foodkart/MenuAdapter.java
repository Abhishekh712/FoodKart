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

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private final List<FoodItem> menu;

    public MenuAdapter(List<FoodItem> menu) {
        this.menu = menu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem item = menu.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(String.format(Locale.US, "₹%.0f", item.getPrice()));
        holder.description.setText(item.getDescription());
        
        // Veg/Non-veg indicator - using simple colored dots or icons
        if (item.isVeg()) {
            holder.vegNonVeg.setImageResource(android.R.drawable.presence_online); // Green-ish dot
        } else {
            holder.vegNonVeg.setImageResource(android.R.drawable.presence_busy); // Red-ish dot
        }

        Glide.with(holder.image.getContext())
            .load(item.getImageUrl())
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.image);

        holder.btnAdd.setOnClickListener(v -> {
            CartRepository.getInstance().addItem(item);
        });
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView vegNonVeg, image;
        TextView name, price, description, btnAdd;

        ViewHolder(View v) {
            super(v);
            vegNonVeg = v.findViewById(R.id.ivVegNonVeg);
            image = v.findViewById(R.id.ivFoodImage);
            name = v.findViewById(R.id.tvFoodName);
            price = v.findViewById(R.id.tvFoodPrice);
            description = v.findViewById(R.id.tvFoodDescription);
            btnAdd = v.findViewById(R.id.btnAddFood);
        }
    }
}