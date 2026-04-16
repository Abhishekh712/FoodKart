package com.example.foodkart;

public class ScoredRestaurant {
    public final Restaurant restaurant;
    public final double score;

    public ScoredRestaurant(Restaurant restaurant, double score) {
        this.restaurant = restaurant;
        this.score = score;
    }
}