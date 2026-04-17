package com.example.foodkart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NormalizationEngine {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Future<FuzzyNumber[]> generateFuzzyVectorAsync(final Restaurant restaurant, final double targetBudget) {
        return executorService.submit(() -> generateFuzzyVector(restaurant, targetBudget));
    }

    public FuzzyNumber[] generateFuzzyVector(Restaurant restaurant, double targetBudget) {
        // 1. Price Score: "Affordability Utility"
        // If it's under target budget, it's 'perfect' (1.0). 
        // If it's over, the score drops sharply.
        double sPrice;
        if (restaurant.getPriceInINR() <= targetBudget) {
            sPrice = 1.0; 
        } else {
            // Smooth decay for items slightly over budget
            sPrice = Math.max(0, 1.0 - (restaurant.getPriceInINR() - targetBudget) / 500.0);
        }
        
        // 2. Quality Score (Fuzzy Rating Logic)
        FuzzyNumber sQuality = calculateFuzzyRatingTFN(restaurant);

        // 3. Distance Score (Linear Proximity)
        double maxDist = 8.0;
        double sDistance = Math.max(0, (maxDist - restaurant.getDistanceInKm()) / maxDist);

        // Convert to TFNs
        FuzzyNumber sPriceTFN = new FuzzyNumber(sPrice, sPrice, sPrice);
        FuzzyNumber sDistanceTFN = new FuzzyNumber(sDistance, sDistance, sDistance);

        return new FuzzyNumber[]{sPriceTFN, sQuality, sDistanceTFN};
    }

    private FuzzyNumber calculateFuzzyRatingTFN(Restaurant restaurant) {
        int n = restaurant.getReviewCount();
        double mean = restaurant.getAverageRating();
        if (n <= 0) return new FuzzyNumber(0, 0, 0);

        double minRating = 3.0;
        double maxRating = 5.0;
        double variance = restaurant.getRatingVariance();
        double delta = (Math.sqrt(variance) / Math.sqrt(n)) * 1.96;

        double normalizedMean = Math.max(0, (mean - minRating) / (maxRating - minRating));
        double normalizedDelta = delta / (maxRating - minRating);

        return new FuzzyNumber(
            Math.max(0, normalizedMean - normalizedDelta),
            normalizedMean,
            Math.min(1.0, normalizedMean + normalizedDelta)
        );
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
