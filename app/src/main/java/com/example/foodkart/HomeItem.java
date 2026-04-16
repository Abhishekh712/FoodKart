package com.example.foodkart;

import java.util.List;

public class HomeItem {
    public static final int TYPE_SEARCH = 0;
    public static final int TYPE_BANNER = 1;
    public static final int TYPE_SECTION_HEADER = 2;
    public static final int TYPE_RESTAURANT = 3;
    public static final int TYPE_BUDGET_SCROLL = 4;

    private int type;
    private String title;
    private Restaurant restaurant;
    private List<Restaurant> budgetRestaurants;

    public HomeItem(int type) {
        this.type = type;
    }

    public HomeItem(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public HomeItem(Restaurant restaurant) {
        this.type = TYPE_RESTAURANT;
        this.restaurant = restaurant;
    }

    public HomeItem(List<Restaurant> budgetRestaurants) {
        this.type = TYPE_BUDGET_SCROLL;
        this.budgetRestaurants = budgetRestaurants;
    }

    public int getType() { return type; }
    public String getTitle() { return title; }
    public Restaurant getRestaurant() { return restaurant; }
    public List<Restaurant> getBudgetRestaurants() { return budgetRestaurants; }
}