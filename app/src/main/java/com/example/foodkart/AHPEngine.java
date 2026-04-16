package com.example.foodkart;

/**
 * Implements the Analytic Hierarchy Process (AHP) for weight derivation.
 */
public class AHPEngine {

    private static final double RI_3 = 0.58;

    public static AHPResult calculateAHP(double[] sliders) {
        int n = sliders.length;
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) matrix[i][j] = 1.0;
                else matrix[i][j] = Math.pow(sliders[i] / sliders[j], 0.477);
            }
        }

        double[] geometricMeans = new double[n];
        double sumOfMeans = 0;
        for (int i = 0; i < n; i++) {
            double product = 1.0;
            for (int j = 0; j < n; j++) product *= matrix[i][j];
            geometricMeans[i] = Math.pow(product, 1.0 / n);
            sumOfMeans += geometricMeans[i];
        }

        double[] weights = new double[n];
        for (int i = 0; i < n; i++) weights[i] = geometricMeans[i] / sumOfMeans;

        double cr = calculateConsistencyRatio(matrix, weights);
        
        // Stability Projection if CR is high
        if (cr > 0.15) {
            for (int i = 0; i < n; i++) weights[i] = (weights[i] + 0.333) / 2.0;
            double newSum = 0;
            for (double w : weights) newSum += w;
            for (int i = 0; i < n; i++) weights[i] /= newSum;
        }

        return new AHPResult(weights, cr);
    }

    private static double calculateConsistencyRatio(double[][] matrix, double[] weights) {
        int n = weights.length;
        double lambdaMax = 0;
        for (int i = 0; i < n; i++) {
            double rowSum = 0;
            for (int j = 0; j < n; j++) rowSum += matrix[i][j] * weights[j];
            lambdaMax += rowSum / weights[i];
        }
        lambdaMax /= n;
        double ci = (lambdaMax - n) / (n - 1);
        return ci / RI_3;
    }
}