package com.example.foodkart;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    public static List<Restaurant> getRestaurants() {
        List<Restaurant> list = new ArrayList<>();

        // Spice Junction
        Restaurant r1 = new Restaurant("r1", "Spice Junction", 250.0, 4.2, 120, 0.45, 3.2, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/spice_junction", "Indian, North Indian", 32);
        r1.setMenu(getSpiceJunctionMenu());
        list.add(r1);

        // Budget Bites
        Restaurant r2 = new Restaurant("r2", "Budget Bites", 200.0, 4.0, 150, 0.3, 2.1, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/budget_bites", "Fast Food, Thali", 25);
        r2.setMenu(getBudgetBitesMenu());
        list.add(r2);

        // Royal Feast
        Restaurant r3 = new Restaurant("r3", "Royal Feast", 1500.0, 4.8, 80, 0.1, 6.5, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/royal_feast", "Fine Dine, Mughlai", 45);
        r3.setMenu(getRoyalFeastMenu());
        list.add(r3);

        // Urban Tandoor
        Restaurant r4 = new Restaurant("r4", "Urban Tandoor", 350.0, 4.3, 200, 0.2, 4.1, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/urban_tandoor", "Tandoor, Indian", 35);
        r4.setMenu(getUrbanTandoorMenu());
        list.add(r4);

        // Street Treats
        Restaurant r5 = new Restaurant("r5", "Street Treats", 150.0, 4.1, 250, 0.5, 1.5, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/street_treats", "Street Food, Snacks", 20);
        r5.setMenu(getStreetTreatsMenu());
        list.add(r5);

        // Golden Spoon
        Restaurant r6 = new Restaurant("r6", "Golden Spoon", 400.0, 3.8, 90, 0.6, 3.8, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/golden_spoon", "Italian, Continental", 38);
        r6.setMenu(getGoldenSpoonMenu());
        list.add(r6);

        // Maharaja Dining
        Restaurant r7 = new Restaurant("r7", "Maharaja Dining", 1200.0, 4.1, 110, 0.25, 6.8, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/maharaja_dining", "Royal Indian, Luxury", 50);
        r7.setMenu(getMaharajaDiningMenu());
        list.add(r7);

        // Green Leaf Café
        Restaurant r8 = new Restaurant("r8", "Green Leaf Café", 250.0, 3.5, 60, 0.7, 2.9, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/green_leaf_cafe", "Healthy, Cafe", 28);
        r8.setMenu(getGreenLeafCafeMenu());
        list.add(r8);

        // Quick Cravings
        Restaurant r9 = new Restaurant("r9", "Quick Cravings", 300.0, 3.9, 300, 0.4, 2.3, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/quick_cravings", "Fast Food, Pizza", 22);
        r9.setMenu(getQuickCravingsMenu());
        list.add(r9);

        // Ocean Delight
        Restaurant r10 = new Restaurant("r10", "Ocean Delight", 700.0, 4.1, 130, 0.35, 5.2, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/ocean_delight", "Seafood, Coastal", 40);
        r10.setMenu(getOceanDelightMenu());
        list.add(r10);

        // Elite Dine
        Restaurant r11 = new Restaurant("r11", "Elite Dine", 1300.0, 4.5, 95, 0.2, 6.2, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/elite_dine", "Premium, European", 48);
        r11.setMenu(getEliteDineMenu());
        list.add(r11);

        // Desi Zaika
        Restaurant r12 = new Restaurant("r12", "Desi Zaika", 250.0, 3.7, 180, 0.4, 3.0, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/desi_zaika", "Home Style, Indian", 30);
        r12.setMenu(getDesiZaikaMenu());
        list.add(r12);

        // Pizza Hub
        Restaurant r13 = new Restaurant("r13", "Pizza Hub", 450.0, 4.3, 220, 0.3, 2.7, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/pizza_hub", "Pizzas, Fast Food", 27);
        r13.setMenu(getPizzaHubMenu());
        list.add(r13);

        // Luxe Bites
        Restaurant r14 = new Restaurant("r14", "Luxe Bites", 1800.0, 4.8, 70, 0.15, 7.0, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/luxe_bites", "Exotic, Luxury", 55);
        r14.setMenu(getLuxeBitesMenu());
        list.add(r14);

        // Daily Meals Corner
        Restaurant r15 = new Restaurant("r15", "Daily Meals Corner", 180.0, 4.0, 140, 0.35, 2.4, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/daily_meals", "Budget, Indian", 26);
        r15.setMenu(getDailyMealsMenu());
        list.add(r15);

        // KFC
        Restaurant r16 = new Restaurant("r16", "KFC", 450.0, 4.3, 500, 0.2, 3.5, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/kfc_logo", "Fried Chicken, Burgers", 30);
        r16.setMenu(getKFCMenu());
        list.add(r16);

        // Pizza Hut
        Restaurant r17 = new Restaurant("r17", "Pizza Hut", 500.0, 4.2, 450, 0.25, 4.0, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/pizza_hut_logo", "Pizzas, Fast Food", 32);
        r17.setMenu(getPizzaHutMenu());
        list.add(r17);

        // Domino\u2019s
        Restaurant r18 = new Restaurant("r18", "Domino\u2019s", 400.0, 4.4, 600, 0.2, 3.2, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/dominos_logo", "Pizzas, Fast Food", 28);
        r18.setMenu(getDominosMenu());
        list.add(r18);

        // Subway
        Restaurant r19 = new Restaurant("r19", "Subway", 350.0, 4.1, 400, 0.3, 2.8, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/subway_logo", "Sandwiches, Healthy", 25);
        r19.setMenu(getSubwayMenu());
        list.add(r19);

        // Burger King
        Restaurant r20 = new Restaurant("r20", "Burger King", 350.0, 4.2, 480, 0.25, 3.0, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/burger_king_logo", "Burgers, Fast Food", 27);
        r20.setMenu(getBurgerKingMenu());
        list.add(r20);

        // McDonald\u2019s
        Restaurant r21 = new Restaurant("r21", "McDonald\u2019s", 300.0, 4.3, 550, 0.2, 2.5, new ArrayList<>(),
                "android.resource://com.example.foodkart/drawable/mcdonalds_logo", "Burgers, Fast Food", 26);
        r21.setMenu(getMcDonaldsMenu());
        list.add(r21);

        return list;
    }

    private static List<FoodItem> getSpiceJunctionMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("sj1", "Paneer Butter Masala", 249, "Rich and creamy paneer dish", "", true));
        menu.add(new FoodItem("sj2", "Chicken Biryani", 299, "Aromatic chicken biryani", "", false));
        menu.add(new FoodItem("sj3", "Veg Fried Rice", 199, "Delicious veg fried rice", "", true));
        menu.add(new FoodItem("sj4", "Chicken Tikka", 279, "Succulent chicken tikka", "", false));
        menu.add(new FoodItem("sj5", "Dal Makhani", 199, "Creamy black lentils", "", true));
        menu.add(new FoodItem("sj6", "Butter Naan", 49, "Soft butter naan", "", true));
        menu.add(new FoodItem("sj7", "Veg Manchurian", 189, "Spicy veg manchurian", "", true));
        menu.add(new FoodItem("sj8", "Chicken Curry", 289, "Classic chicken curry", "", false));
        menu.add(new FoodItem("sj9", "Jeera Rice", 149, "Fragrant jeera rice", "", true));
        menu.add(new FoodItem("sj10", "Gulab Jamun", 99, "Sweet gulab jamun", "", true));
        return menu;
    }

    private static List<FoodItem> getBudgetBitesMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("bb1", "Veg Thali", 199, "Complete veg thali", "", true));
        menu.add(new FoodItem("bb2", "Chicken Thali", 299, "Complete chicken thali", "", false));
        menu.add(new FoodItem("bb3", "Paneer Roll", 149, "Spicy paneer roll", "", true));
        menu.add(new FoodItem("bb4", "Chicken Roll", 179, "Juicy chicken roll", "", false));
        menu.add(new FoodItem("bb5", "Veg Pulao", 199, "Healthy veg pulao", "", true));
        menu.add(new FoodItem("bb6", "Egg Curry", 159, "Classic egg curry", "", false));
        menu.add(new FoodItem("bb7", "Dal Fry", 129, "Tempered yellow lentils", "", true));
        menu.add(new FoodItem("bb8", "Chapati (2 pcs)", 49, "Soft whole wheat chapatis", "", true));
        menu.add(new FoodItem("bb9", "Veg Burger", 119, "Crispy veg burger", "", true));
        menu.add(new FoodItem("bb10", "Cold Coffee", 99, "Refreshing cold coffee", "", true));
        return menu;
    }

    private static List<FoodItem> getRoyalFeastMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("rf1", "Lobster Thermidor", 1299, "Creamy lobster thermidor", "", false));
        menu.add(new FoodItem("rf2", "Mutton Rogan Josh", 899, "Authentic kashmiri mutton", "", false));
        menu.add(new FoodItem("rf3", "Chicken Chettinad", 799, "Spicy south indian chicken", "", false));
        menu.add(new FoodItem("rf4", "Paneer Lababdar", 699, "Creamy paneer lababdar", "", true));
        menu.add(new FoodItem("rf5", "Fish Curry", 899, "Regional fish curry", "", false));
        menu.add(new FoodItem("rf6", "Butter Garlic Prawns", 1099, "Sizzling butter garlic prawns", "", false));
        menu.add(new FoodItem("rf7", "Hyderabadi Biryani", 699, "Slow cooked hyderabadi biryani", "", false));
        menu.add(new FoodItem("rf8", "Malai Kofta", 599, "Creamy malai kofta", "", true));
        menu.add(new FoodItem("rf9", "Tandoori Platter", 999, "Assorted tandoori items", "", false));
        menu.add(new FoodItem("rf10", "Shahi Tukda", 499, "Rich bread pudding", "", true));
        return menu;
    }

    private static List<FoodItem> getUrbanTandoorMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ut1", "Paneer Tikka", 299, "", "", true));
        menu.add(new FoodItem("ut2", "Chicken Tandoori", 399, "", "", false));
        menu.add(new FoodItem("ut3", "Veg Biryani", 249, "", "", true));
        menu.add(new FoodItem("ut4", "Mutton Curry", 499, "", "", false));
        menu.add(new FoodItem("ut5", "Dal Tadka", 199, "", "", true));
        menu.add(new FoodItem("ut6", "Butter Naan", 59, "", "", true));
        menu.add(new FoodItem("ut7", "Garlic Naan", 79, "", "", true));
        menu.add(new FoodItem("ut8", "Chicken Masala", 349, "", "", false));
        menu.add(new FoodItem("ut9", "Veg Korma", 229, "", "", true));
        menu.add(new FoodItem("ut10", "Rasgulla", 129, "", "", true));
        return menu;
    }

    private static List<FoodItem> getStreetTreatsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("st1", "Pav Bhaji", 149, "", "", true));
        menu.add(new FoodItem("st2", "Vada Pav (2 pcs)", 99, "", "", true));
        menu.add(new FoodItem("st3", "Misal Pav", 129, "", "", true));
        menu.add(new FoodItem("st4", "Veg Sandwich", 119, "", "", true));
        menu.add(new FoodItem("st5", "Cheese Sandwich", 149, "", "", true));
        menu.add(new FoodItem("st6", "Masala Dosa", 129, "", "", true));
        menu.add(new FoodItem("st7", "Idli Sambar", 99, "", "", true));
        menu.add(new FoodItem("st8", "Samosa (2 pcs)", 79, "", "", true));
        menu.add(new FoodItem("st9", "Chole Bhature", 179, "", "", true));
        menu.add(new FoodItem("st10", "Lassi", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getGoldenSpoonMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("gs1", "Chicken Alfredo Pasta", 399, "", "", false));
        menu.add(new FoodItem("gs2", "Veg Lasagna", 349, "", "", true));
        menu.add(new FoodItem("gs3", "Margherita Pizza", 299, "", "", true));
        menu.add(new FoodItem("gs4", "Pepperoni Pizza", 499, "", "", false));
        menu.add(new FoodItem("gs5", "Garlic Bread", 199, "", "", true));
        menu.add(new FoodItem("gs6", "Caesar Salad", 249, "", "", true));
        menu.add(new FoodItem("gs7", "Grilled Chicken", 449, "", "", false));
        menu.add(new FoodItem("gs8", "Mushroom Soup", 199, "", "", true));
        menu.add(new FoodItem("gs9", "Chocolate Cake", 249, "", "", true));
        menu.add(new FoodItem("gs10", "Cold Coffee", 179, "", "", true));
        return menu;
    }

    private static List<FoodItem> getMaharajaDiningMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("md1", "Royal Thali", 1499, "", "", true));
        menu.add(new FoodItem("md2", "Mutton Biryani", 999, "", "", false));
        menu.add(new FoodItem("md3", "Butter Chicken", 899, "", "", false));
        menu.add(new FoodItem("md4", "Paneer Royal", 799, "", "", true));
        menu.add(new FoodItem("md5", "Chicken Seekh Kebab", 899, "", "", false));
        menu.add(new FoodItem("md6", "Fish Fry", 999, "", "", false));
        menu.add(new FoodItem("md7", "Dal Maharani", 699, "", "", true));
        menu.add(new FoodItem("md8", "Stuffed Naan", 199, "", "", true));
        menu.add(new FoodItem("md9", "Shahi Paneer", 799, "", "", true));
        menu.add(new FoodItem("md10", "Rabdi", 499, "", "", true));
        return menu;
    }

    private static List<FoodItem> getGreenLeafCafeMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("glc1", "Veg Salad Bowl", 199, "", "", true));
        menu.add(new FoodItem("glc2", "Paneer Wrap", 249, "", "", true));
        menu.add(new FoodItem("glc3", "Veg Burger", 199, "", "", true));
        menu.add(new FoodItem("glc4", "Pasta Primavera", 299, "", "", true));
        menu.add(new FoodItem("glc5", "Veg Soup", 149, "", "", true));
        menu.add(new FoodItem("glc6", "Grilled Sandwich", 199, "", "", true));
        menu.add(new FoodItem("glc7", "Smoothie", 179, "", "", true));
        menu.add(new FoodItem("glc8", "Brownie", 199, "", "", true));
        menu.add(new FoodItem("glc9", "Garlic Bread", 149, "", "", true));
        menu.add(new FoodItem("glc10", "Iced Tea", 129, "", "", true));
        return menu;
    }

    private static List<FoodItem> getQuickCravingsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("qc1", "Chicken Burger", 199, "", "", false));
        menu.add(new FoodItem("qc2", "Veg Burger", 149, "", "", true));
        menu.add(new FoodItem("qc3", "French Fries", 129, "", "", true));
        menu.add(new FoodItem("qc4", "Chicken Nuggets", 199, "", "", false));
        menu.add(new FoodItem("qc5", "Veg Pizza", 249, "", "", true));
        menu.add(new FoodItem("qc6", "Chicken Pizza", 299, "", "", false));
        menu.add(new FoodItem("qc7", "Cold Drink", 99, "", "", true));
        menu.add(new FoodItem("qc8", "Milkshake", 149, "", "", true));
        menu.add(new FoodItem("qc9", "Hot Dog", 179, "", "", false));
        menu.add(new FoodItem("qc10", "Cheese Fries", 159, "", "", true));
        return menu;
    }

    private static List<FoodItem> getOceanDelightMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("od1", "Grilled Fish", 599, "", "", false));
        menu.add(new FoodItem("od2", "Prawn Curry", 699, "", "", false));
        menu.add(new FoodItem("od3", "Fish Biryani", 499, "", "", false));
        menu.add(new FoodItem("od4", "Crab Masala", 799, "", "", false));
        menu.add(new FoodItem("od5", "Butter Garlic Fish", 649, "", "", false));
        menu.add(new FoodItem("od6", "Rice", 199, "", "", true));
        menu.add(new FoodItem("od7", "Fish Fry", 499, "", "", false));
        menu.add(new FoodItem("od8", "Veg Salad", 199, "", "", true));
        menu.add(new FoodItem("od9", "Soup", 249, "", "", true));
        menu.add(new FoodItem("od10", "Ice Cream", 199, "", "", true));
        return menu;
    }

    private static List<FoodItem> getEliteDineMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ed1", "Steak", 1299, "", "", false));
        menu.add(new FoodItem("ed2", "Grilled Salmon", 1199, "", "", false));
        menu.add(new FoodItem("ed3", "Chicken Steak", 899, "", "", false));
        menu.add(new FoodItem("ed4", "Veg Steak", 799, "", "", true));
        menu.add(new FoodItem("ed5", "Pasta Alfredo", 699, "", "", true));
        menu.add(new FoodItem("ed6", "Risotto", 899, "", "", true));
        menu.add(new FoodItem("ed7", "Soup", 399, "", "", true));
        menu.add(new FoodItem("ed8", "Salad", 499, "", "", true));
        menu.add(new FoodItem("ed9", "Cheesecake", 599, "", "", true));
        menu.add(new FoodItem("ed10", "Mocktail", 399, "", "", true));
        return menu;
    }

    private static List<FoodItem> getDesiZaikaMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dz1", "Rajma Chawal", 199, "", "", true));
        menu.add(new FoodItem("dz2", "Chole Chawal", 199, "", "", true));
        menu.add(new FoodItem("dz3", "Paneer Masala", 249, "", "", true));
        menu.add(new FoodItem("dz4", "Chicken Curry", 299, "", "", false));
        menu.add(new FoodItem("dz5", "Dal Fry", 149, "", "", true));
        menu.add(new FoodItem("dz6", "Chapati", 49, "", "", true));
        menu.add(new FoodItem("dz7", "Jeera Rice", 149, "", "", true));
        menu.add(new FoodItem("dz8", "Aloo Gobi", 199, "", "", true));
        menu.add(new FoodItem("dz9", "Kheer", 129, "", "", true));
        menu.add(new FoodItem("dz10", "Lassi", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getPizzaHubMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ph1", "Margherita", 249, "", "", true));
        menu.add(new FoodItem("ph2", "Farmhouse", 349, "", "", true));
        menu.add(new FoodItem("ph3", "Pepperoni", 399, "", "", false));
        menu.add(new FoodItem("ph4", "BBQ Chicken", 449, "", "", false));
        menu.add(new FoodItem("ph5", "Veg Extravaganza", 379, "", "", true));
        menu.add(new FoodItem("ph6", "Garlic Bread", 199, "", "", true));
        menu.add(new FoodItem("ph7", "Cheese Burst Pizza", 499, "", "", true));
        menu.add(new FoodItem("ph8", "Pasta", 299, "", "", true));
        menu.add(new FoodItem("ph9", "Choco Lava Cake", 149, "", "", true));
        menu.add(new FoodItem("ph10", "Cold Drink", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getLuxeBitesMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("lb1", "Wagyu Steak", 1499, "", "", false));
        menu.add(new FoodItem("lb2", "Lobster Grill", 1399, "", "", false));
        menu.add(new FoodItem("lb3", "Truffle Pasta", 1099, "", "", true));
        menu.add(new FoodItem("lb4", "Premium Sushi Platter", 1299, "", "", false));
        menu.add(new FoodItem("lb5", "Caviar Salad", 1499, "", "", false));
        menu.add(new FoodItem("lb6", "Grilled Chicken Deluxe", 999, "", "", false));
        menu.add(new FoodItem("lb7", "Exotic Veg Platter", 899, "", "", true));
        menu.add(new FoodItem("lb8", "Cheese Board", 799, "", "", true));
        menu.add(new FoodItem("lb9", "Tiramisu", 699, "", "", true));
        menu.add(new FoodItem("lb10", "Signature Cocktail", 599, "", "", true));
        return menu;
    }

    private static List<FoodItem> getDailyMealsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dm1", "Veg Thali", 179, "", "", true));
        menu.add(new FoodItem("dm2", "Chicken Thali", 299, "", "", false));
        menu.add(new FoodItem("dm3", "Egg Curry", 149, "", "", false));
        menu.add(new FoodItem("dm4", "Dal Rice", 129, "", "", true));
        menu.add(new FoodItem("dm5", "Paneer Curry", 249, "", "", true));
        menu.add(new FoodItem("dm6", "Chapati", 49, "", "", true));
        menu.add(new FoodItem("dm7", "Veg Pulao", 199, "", "", true));
        menu.add(new FoodItem("dm8", "Chicken Curry", 299, "", "", false));
        menu.add(new FoodItem("dm9", "Curd Rice", 149, "", "", true));
        menu.add(new FoodItem("dm10", "Gulab Jamun", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getKFCMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("kfc1", "Zinger Burger", 219, "", "", false));
        menu.add(new FoodItem("kfc2", "Hot Wings (6 pcs)", 299, "", "", false));
        menu.add(new FoodItem("kfc3", "Chicken Bucket (10 pcs)", 699, "", "", false));
        menu.add(new FoodItem("kfc4", "Popcorn Chicken", 249, "", "", false));
        menu.add(new FoodItem("kfc5", "Chicken Strips (3 pcs)", 199, "", "", false));
        menu.add(new FoodItem("kfc6", "Veg Zinger", 199, "", "", true));
        menu.add(new FoodItem("kfc7", "French Fries", 129, "", "", true));
        menu.add(new FoodItem("kfc8", "Chicken Roll", 179, "", "", false));
        menu.add(new FoodItem("kfc9", "Krusher Drink", 149, "", "", true));
        menu.add(new FoodItem("kfc10", "Chocolate Lava Cake", 109, "", "", true));
        return menu;
    }

    private static List<FoodItem> getPizzaHutMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("phu1", "Margherita Pizza", 249, "", "", true));
        menu.add(new FoodItem("phu2", "Veggie Supreme", 399, "", "", true));
        menu.add(new FoodItem("phu3", "Chicken Supreme", 499, "", "", false));
        menu.add(new FoodItem("phu4", "Paneer Tikka Pizza", 449, "", "", true));
        menu.add(new FoodItem("phu5", "Garlic Bread", 199, "", "", true));
        menu.add(new FoodItem("phu6", "Cheese Garlic Bread", 249, "", "", true));
        menu.add(new FoodItem("phu7", "Veg Pasta", 299, "", "", true));
        menu.add(new FoodItem("phu8", "Chicken Pasta", 349, "", "", false));
        menu.add(new FoodItem("phu9", "Choco Lava Cake", 149, "", "", true));
        menu.add(new FoodItem("phu10", "Pepsi", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getDominosMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dom1", "Margherita Pizza", 199, "", "", true));
        menu.add(new FoodItem("dom2", "Farmhouse Pizza", 349, "", "", true));
        menu.add(new FoodItem("dom3", "Peppy Paneer Pizza", 379, "", "", true));
        menu.add(new FoodItem("dom4", "Chicken Dominator", 599, "", "", false));
        menu.add(new FoodItem("dom5", "Veg Extravaganza", 399, "", "", true));
        menu.add(new FoodItem("dom6", "Garlic Breadsticks", 199, "", "", true));
        menu.add(new FoodItem("dom7", "Stuffed Garlic Bread", 249, "", "", true));
        menu.add(new FoodItem("dom8", "Pasta Italiano Veg", 299, "", "", true));
        menu.add(new FoodItem("dom9", "Choco Lava Cake", 149, "", "", true));
        menu.add(new FoodItem("dom10", "Coke", 99, "", "", true));
        return menu;
    }

    private static List<FoodItem> getSubwayMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("sub1", "Veggie Delight Sub", 199, "", "", true));
        menu.add(new FoodItem("sub2", "Paneer Tikka Sub", 249, "", "", true));
        menu.add(new FoodItem("sub3", "Chicken Teriyaki Sub", 299, "", "", false));
        menu.add(new FoodItem("sub4", "Chicken Seekh Sub", 279, "", "", false));
        menu.add(new FoodItem("sub5", "Aloo Patty Sub", 179, "", "", true));
        menu.add(new FoodItem("sub6", "Tuna Sub", 349, "", "", false));
        menu.add(new FoodItem("sub7", "Salad Bowl", 199, "", "", true));
        menu.add(new FoodItem("sub8", "Cookies (2 pcs)", 129, "", "", true));
        menu.add(new FoodItem("sub9", "Cold Drink", 99, "", "", true));
        menu.add(new FoodItem("sub10", "Wrap", 249, "", "", true));
        return menu;
    }

    private static List<FoodItem> getBurgerKingMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("bk1", "Veg Whopper", 179, "", "", true));
        menu.add(new FoodItem("bk2", "Chicken Whopper", 219, "", "", false));
        menu.add(new FoodItem("bk3", "Crispy Veg Burger", 129, "", "", true));
        menu.add(new FoodItem("bk4", "Chicken Crispy Burger", 149, "", "", false));
        menu.add(new FoodItem("bk5", "French Fries", 129, "", "", true));
        menu.add(new FoodItem("bk6", "Veg Nuggets", 149, "", "", true));
        menu.add(new FoodItem("bk7", "Chicken Nuggets", 179, "", "", false));
        menu.add(new FoodItem("bk8", "Chocolate Shake", 179, "", "", true));
        menu.add(new FoodItem("bk9", "Cold Drink", 99, "", "", true));
        menu.add(new FoodItem("bk10", "Cheese Burger", 159, "", "", true));
        return menu;
    }

    private static List<FoodItem> getMcDonaldsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("mc1", "McAloo Tikki Burger", 99, "", "", true));
        menu.add(new FoodItem("mc2", "McChicken Burger", 149, "", "", false));
        menu.add(new FoodItem("mc3", "Filet-O-Fish", 199, "", "", false));
        menu.add(new FoodItem("mc4", "McVeggie Burger", 149, "", "", true));
        menu.add(new FoodItem("mc5", "Chicken McNuggets (6 pcs)", 199, "", "", false));
        menu.add(new FoodItem("mc6", "French Fries", 129, "", "", true));
        menu.add(new FoodItem("mc7", "Veg Pizza McPuff", 49, "", "", true));
        menu.add(new FoodItem("mc8", "McFlurry", 129, "", "", true));
        menu.add(new FoodItem("mc9", "Cold Coffee", 149, "", "", true));
        menu.add(new FoodItem("mc10", "Coke", 99, "", "", true));
        return menu;
    }
}
