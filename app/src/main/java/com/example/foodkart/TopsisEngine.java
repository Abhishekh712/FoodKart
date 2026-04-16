package com.example.foodkart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Implements the Fuzzy TOPSIS Engine for multi-criteria decision making.
 */
public class TopsisEngine {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Future<List<Restaurant>> rankRestaurantsAsync(final DecisionState state, final double[] weights) {
        return executorService.submit(new Callable<List<Restaurant>>() {
            @Override
            public List<Restaurant> call() {
                return rankRestaurants(state, weights);
            }
        });
    }

    public List<Restaurant> rankRestaurants(DecisionState state, double[] weights) {
        List<Restaurant> restaurants = state.getRestaurantList();
        int numCriteria = weights.length;
        int numRestaurants = restaurants.size();

        if (numRestaurants == 0) return new ArrayList<>();

        // 1. Identify FPIS (Fuzzy Positive Ideal Solution) and FNIS (Fuzzy Negative Ideal Solution)
        // Since we normalized all criteria to be "benefit" (higher is better), 
        // FPIS = max(u) and FNIS = min(l) across all restaurants for each criterion.
        FuzzyNumber[] fpis = new FuzzyNumber[numCriteria];
        FuzzyNumber[] fnis = new FuzzyNumber[numCriteria];

        for (int j = 0; j < numCriteria; j++) {
            double maxU = Double.MIN_VALUE;
            double minL = Double.MAX_VALUE;
            for (Restaurant r : restaurants) {
                FuzzyNumber[] vector = state.getVector(r.getId());
                if (vector == null) continue;
                maxU = Math.max(maxU, vector[j].getU());
                minL = Math.min(minL, vector[j].getL());
            }
            fpis[j] = new FuzzyNumber(maxU, maxU, maxU);
            fnis[j] = new FuzzyNumber(minL, minL, minL);
        }

        // 2. Calculate distances and Closeness Coefficient (CCi)
        final List<ScoredRestaurant> scoredList = new ArrayList<>();
        for (Restaurant r : restaurants) {
            FuzzyNumber[] vector = state.getVector(r.getId());
            if (vector == null) continue;

            double dp = 0; // Distance to FPIS
            double dn = 0; // Distance to FNIS

            for (int j = 0; j < numCriteria; j++) {
                // Weighted distance using Vertex Method
                double distP = calculateVertexDistance(vector[j], fpis[j]) * weights[j];
                double distN = calculateVertexDistance(vector[j], fnis[j]) * weights[j];
                dp += distP;
                dn += distN;
            }

            double cci = dn / (dp + dn); // Closeness Coefficient
            scoredList.add(new ScoredRestaurant(r, cci));
        }

        // 3. Sort by CCi descending
        Collections.sort(scoredList, (a, b) -> Double.compare(b.score, a.score));

        List<Restaurant> sortedRestaurants = new ArrayList<>();
        for (ScoredRestaurant sr : scoredList) {
            sortedRestaurants.add(sr.restaurant);
        }

        return sortedRestaurants;
    }

    /**
     * Vertex method to calculate distance between two Triangular Fuzzy Numbers.
     */
    private double calculateVertexDistance(FuzzyNumber a, FuzzyNumber b) {
        return Math.sqrt((1.0/3.0) * (
                Math.pow(a.getL() - b.getL(), 2) +
                Math.pow(a.getM() - b.getM(), 2) +
                Math.pow(a.getU() - b.getU(), 2)
        ));
    }

    private static class ScoredRestaurant {
        Restaurant restaurant;
        double score;

        ScoredRestaurant(Restaurant restaurant, double score) {
            this.restaurant = restaurant;
            this.score = score;
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}