package com.example.foodkart;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.Locale;
import java.util.List;

public class HomeAdapter extends ListAdapter<HomeItem, RecyclerView.ViewHolder> {

    public HomeAdapter() {
        super(new DiffUtil.ItemCallback<HomeItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
                if (oldItem.getType() != newItem.getType()) return false;
                if (oldItem.getType() == HomeItem.TYPE_RESTAURANT) {
                    return oldItem.getRestaurant().getId().equals(newItem.getRestaurant().getId());
                }
                if (oldItem.getType() == HomeItem.TYPE_BUDGET_SCROLL) return true;
                if (oldItem.getType() == HomeItem.TYPE_SECTION_HEADER) {
                    return oldItem.getTitle() != null && oldItem.getTitle().equals(newItem.getTitle());
                }
                return true;
            }

            @Override
            public boolean areContentsTheSame(@NonNull HomeItem oldItem, @NonNull HomeItem newItem) {
                if (oldItem.getType() == HomeItem.TYPE_RESTAURANT) {
                    Restaurant r1 = oldItem.getRestaurant();
                    Restaurant r2 = newItem.getRestaurant();
                    return r1.getName().equals(r2.getName()) && 
                           r1.getAverageRating() == r2.getAverageRating() &&
                           (oldItem.getFilterType() == null ? newItem.getFilterType() == null : oldItem.getFilterType().equals(newItem.getFilterType()));
                }
                return true;
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case HomeItem.TYPE_SEARCH:
                return new SearchViewHolder(inflater.inflate(R.layout.item_home_search, parent, false));
            case HomeItem.TYPE_BANNER:
                return new BannerViewHolder(inflater.inflate(R.layout.item_home_banner, parent, false));
            case HomeItem.TYPE_SECTION_HEADER:
                return new SectionHeaderViewHolder(inflater.inflate(R.layout.item_home_section_header, parent, false));
            case HomeItem.TYPE_BUDGET_SCROLL:
                return new BudgetScrollViewHolder(inflater.inflate(R.layout.item_home_horizontal_list, parent, false));
            case HomeItem.TYPE_RESTAURANT:
            default:
                return new RestaurantViewHolder(inflater.inflate(R.layout.item_restaurant, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeItem item = getItem(position);
        if (holder instanceof RestaurantViewHolder) {
            ((RestaurantViewHolder) holder).bind(item, position);
        } else if (holder instanceof SectionHeaderViewHolder) {
            ((SectionHeaderViewHolder) holder).title.setText(item.getTitle());
        } else if (holder instanceof BudgetScrollViewHolder) {
            ((BudgetScrollViewHolder) holder).bind(item.getBudgetRestaurants(), item.getFilterType());
        }
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {
        SearchViewHolder(View v) { 
            super(v);
            v.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                view.getContext().startActivity(intent);
            });
        }
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        BannerViewHolder(View v) { super(v); }
    }

    static class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        SectionHeaderViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.sectionTitle);
        }
    }

    static class BudgetScrollViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rv;
        BudgetScrollViewHolder(View v) {
            super(v);
            rv = v.findViewById(R.id.horizontalRecyclerView);
            rv.setLayoutManager(new LinearLayoutManager(v.getContext(), LinearLayoutManager.HORIZONTAL, false));
        }
        void bind(java.util.List<Restaurant> list, String filterType) {
            rv.setAdapter(new BudgetAdapter(list, filterType));
        }
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, cuisine, rating, time, price, reason, badge;

        RestaurantViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.restaurantImage);
            name = v.findViewById(R.id.restaurantName);
            cuisine = v.findViewById(R.id.restaurantCuisine);
            rating = v.findViewById(R.id.restaurantRating);
            time = v.findViewById(R.id.deliveryTime);
            price = v.findViewById(R.id.priceForTwo);
            reason = v.findViewById(R.id.recommendationReason);
            badge = v.findViewById(R.id.restaurantBadge);
        }

        void bind(HomeItem item, int pos) {
            Restaurant r = item.getRestaurant();
            name.setText(r.getName());
            cuisine.setText(r.getCuisine());
            rating.setText(String.format(Locale.US, "%.1f ★", r.getAverageRating()));
            time.setText(String.format(Locale.US, "%d mins", r.getDeliveryTimeMin()));
            price.setText(String.format(Locale.US, "₹%.0f for two", r.getPriceInINR()));
            
            Glide.with(image.getContext())
                .load(r.getImageUrl())
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(image);

            reason.setText(generateReason(r, pos));

            if (pos <= 5 && pos >= 2) { 
                 badge.setVisibility(View.VISIBLE);
                 badge.setText("BEST MATCH");
            } else if (r.getDeliveryTimeMin() < 30) {
                 badge.setVisibility(View.VISIBLE);
                 badge.setText("FAST DELIVERY");
            } else {
                 badge.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
                intent.putExtra(RestaurantDetailActivity.EXTRA_RESTAURANT_ID, r.getId());
                intent.putExtra(RestaurantDetailActivity.EXTRA_FILTER_TYPE, item.getFilterType());
                v.getContext().startActivity(intent);
            });
        }

        private String generateReason(Restaurant r, int pos) {
            if (pos <= 5) return "✔ Matches your preference for price and quality.";
            if (r.getPriceInINR() < 400) return "💰 Excellent budget-friendly option.";
            if (r.getAverageRating() > 4.2) return "⭐ Highly rated by local foodies.";
            return "📍 Conveniently located near you.";
        }
    }
}
