package com.example.foodkart;

import android.content.Intent;
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

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {
    private final List<Restaurant> restaurants;

    public BudgetAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_budget_restaurant, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant r = restaurants.get(position);
        holder.name.setText(r.getName());
        holder.price.setText(String.format(Locale.US, "₹%.0f for two", r.getPriceInINR()));
        
        Glide.with(holder.image.getContext())
            .load(r.getImageUrl())
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
            intent.putExtra(RestaurantDetailActivity.EXTRA_RESTAURANT_ID, r.getId());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        ViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.budgetImage);
            name = v.findViewById(R.id.budgetName);
            price = v.findViewById(R.id.budgetPrice);
        }
    }
}