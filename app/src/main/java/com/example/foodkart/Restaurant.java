package com.example.foodkart;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String id;
    private String name;
    private double priceInINR;
    private double averageRating;
    private int reviewCount;
    private double ratingVariance;
    private double distanceInKm;
    private List<UserInteraction> userInteractions;

    private String imageUrl;
    private String cuisine;
    private int deliveryTimeMin;
    private List<FoodItem> menu;

    public Restaurant(String id, String name, double priceInINR, double averageRating, int reviewCount, double ratingVariance, double distanceInKm, List<UserInteraction> userInteractions, String imageUrl, String cuisine, int deliveryTimeMin) {
        this.id = id;
        this.name = name;
        this.priceInINR = priceInINR;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
        this.ratingVariance = ratingVariance;
        this.distanceInKm = distanceInKm;
        this.userInteractions = userInteractions;
        this.imageUrl = imageUrl;
        this.cuisine = cuisine;
        this.deliveryTimeMin = deliveryTimeMin;
        this.menu = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPriceInINR() { return priceInINR; }
    public double getAverageRating() { return averageRating; }
    public int getReviewCount() { return reviewCount; }
    public double getRatingVariance() { return ratingVariance; }
    public double getDistanceInKm() { return distanceInKm; }
    public List<UserInteraction> getUserInteractions() { return userInteractions; }
    public String getImageUrl() { return imageUrl; }
    public String getCuisine() { return cuisine; }
    public int getDeliveryTimeMin() { return deliveryTimeMin; }
    
    public List<FoodItem> getMenu() { return menu; }
    public void setMenu(List<FoodItem> menu) { this.menu = menu; }
}