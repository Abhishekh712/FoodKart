package com.example.foodkart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Caches normalized feature vectors to avoid redundant Phase 1 processing.
 * This represents the "State Management" part of Phase 2.
 */
public class DecisionState {
    private final Map<String, FuzzyNumber[]> normalizedVectors = new HashMap<>();
    private final List<Restaurant> restaurantList;

    public DecisionState(List<Restaurant> restaurants) {
        this.restaurantList = restaurants;
    }

    public void cacheVector(String restaurantId, FuzzyNumber[] vector) {
        normalizedVectors.put(restaurantId, vector);
    }

    public FuzzyNumber[] getVector(String restaurantId) {
        return normalizedVectors.get(restaurantId);
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public boolean isComplete() {
        return normalizedVectors.size() == restaurantList.size();
    }
}