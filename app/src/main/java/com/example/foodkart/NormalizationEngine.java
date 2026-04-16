package com.example.foodkart;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NormalizationEngine {

    private final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public Future<FuzzyNumber[]> generateFuzzyVectorAsync(final Restaurant restaurant, final double targetBudget) {
        return executorService.submit(() -> generateFuzzyVector(restaurant, targetBudget));
    }

    public FuzzyNumber[] generateFuzzyVector(Restaurant restaurant, double targetBudget) {
        // 1. Price Score (Gaussian Anchor)
        double sPrice = calculateGaussianPriceScore(restaurant.getPriceInINR(), targetBudget);
        
        // 2. Quality Score (Fuzzy Rating Logic)
        FuzzyNumber sQuality = calculateFuzzyRatingTFN(restaurant);

        // 3. Distance Score (Inverse Normalization - closer is better)
        double sDistance = 1.0 / (1.0 + restaurant.getDistanceInKm());

        // Convert crisp scores to TFNs (l=m=u)
        FuzzyNumber sPriceTFN = new FuzzyNumber(sPrice, sPrice, sPrice);
        FuzzyNumber sDistanceTFN = new FuzzyNumber(sDistance, sDistance, sDistance);

        return new FuzzyNumber[]{sPriceTFN, sQuality, sDistanceTFN};
    }

    private double calculateGaussianPriceScore(double price, double targetBudget) {
        double lowerBound = targetBudget * 0.9;
        double upperBound = targetBudget * 1.1;

        if (price >= lowerBound && price <= upperBound) {
            return 1.0;
        }

        double diff = (price < lowerBound) ? (lowerBound - price) : (price - upperBound);
        double sigma = 0.2 * targetBudget;
        return Math.exp(-(diff * diff) / (2 * sigma * sigma));
    }

    private FuzzyNumber calculateFuzzyRatingTFN(Restaurant restaurant) {
        int n = restaurant.getReviewCount();
        double mean = restaurant.getAverageRating();
        
        if (n <= 0) return new FuzzyNumber(0, 0, 0);

        double variance = restaurant.getRatingVariance();
        double delta = (Math.sqrt(variance) / Math.sqrt(n)) * 1.96;

        double normalizedMean = mean / 5.0;
        double normalizedDelta = delta / 5.0;

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