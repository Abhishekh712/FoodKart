package com.example.foodkart;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;
import java.util.List;

public class RestaurantAdapter extends ListAdapter<Restaurant, RestaurantAdapter.ViewHolder> {

    public RestaurantAdapter() {
        super(new DiffUtil.ItemCallback<Restaurant>() {
            @Override
            public boolean areItemsTheSame(@NonNull Restaurant oldItem, @NonNull Restaurant newItem) {
                return oldItem.getId().equals(newItem.getId());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Restaurant oldItem, @NonNull Restaurant newItem) {
                return oldItem.getName().equals(newItem.getName()) && 
                       oldItem.getAverageRating() == newItem.getAverageRating();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = getItem(position);
        holder.name.setText(restaurant.getName());
        holder.cuisine.setText(restaurant.getCuisine());
        holder.rating.setText(String.format(Locale.US, "%.1f ★", restaurant.getAverageRating()));
        holder.deliveryTime.setText(restaurant.getDeliveryTimeMin() + " mins");
        holder.priceForTwo.setText(String.format(Locale.US, "₹%.0f for two", restaurant.getPriceInINR()));
        
        // Explainability Logic
        String reason = generateReason(restaurant, position);
        holder.reason.setText(reason);

        // Dynamic Badge
        if (position == 0) {
            holder.badge.setVisibility(View.VISIBLE);
            holder.badge.setText("Best Overall Match");
        } else if (restaurant.getDistanceInKm() < 1.0) {
            holder.badge.setVisibility(View.VISIBLE);
            holder.badge.setText("Ultra Fast");
        } else {
            holder.badge.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
            intent.putExtra(RestaurantDetailActivity.EXTRA_RESTAURANT_ID, restaurant.getId());
            v.getContext().startActivity(intent);
        });
    }

    private String generateReason(Restaurant r, int pos) {
        if (pos == 0) return "Matches your preference for price and quality.";
        if (r.getPriceInINR() < 400) return "Excellent budget-friendly option.";
        if (r.getAverageRating() > 4.2) return "Highly rated by local foodies.";
        return "Good balance for your current settings.";
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, cuisine, rating, deliveryTime, priceForTwo, reason, badge;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.restaurantName);
            cuisine = itemView.findViewById(R.id.restaurantCuisine);
            rating = itemView.findViewById(R.id.restaurantRating);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
            priceForTwo = itemView.findViewById(R.id.priceForTwo);
            reason = itemView.findViewById(R.id.recommendationReason);
            badge = itemView.findViewById(R.id.restaurantBadge);
        }
    }
}