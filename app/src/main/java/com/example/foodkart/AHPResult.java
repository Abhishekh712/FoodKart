package com.example.foodkart;

public class AHPResult {
    public final double[] weights;
    public final double consistencyRatio;

    public AHPResult(double[] weights, double consistencyRatio) {
        this.weights = weights;
        this.consistencyRatio = consistencyRatio;
    }
}