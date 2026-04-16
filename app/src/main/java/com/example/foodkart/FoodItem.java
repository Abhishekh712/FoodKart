package com.example.foodkart;

public class FoodItem {
    private String id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private boolean isVeg;

    public FoodItem(String id, String name, double price, String description, String imageUrl, boolean isVeg) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isVeg = isVeg;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public boolean isVeg() { return isVeg; }
}