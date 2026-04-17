package com.example.foodkart;

import java.util.ArrayList;
import java.util.List;

public class MockData {

    private static String getFoodImage(String name) {
        String n = name.toLowerCase();
        if (n.contains("pizza") || n.contains("margherita") || n.contains("farmhouse")) 
            return "https://images.unsplash.com/photo-1513104890138-7c749659a591";
        if (n.contains("burger") || n.contains("zinger") || n.contains("whopper") || n.contains("tikki")) 
            return "https://images.unsplash.com/photo-1568901346375-23c9450c58cd";
        if (n.contains("biryani") || n.contains("rice") || n.contains("pulao") || n.contains("chawal")) 
            return "https://images.unsplash.com/photo-1563379091339-03b21bc4a4f8";
        if (n.contains("chicken") || n.contains("tikka") || n.contains("wings") || n.contains("nuggets") || n.contains("strips")) 
            return "https://images.unsplash.com/photo-1562967914-608f82629710";
        if (n.contains("paneer") || n.contains("dal") || n.contains("thali") || n.contains("curry") || n.contains("masala")) 
            return "https://images.unsplash.com/photo-1589302168068-964664d93dc0";
        if (n.contains("sandwich") || n.contains("sub") || n.contains("wrap") || n.contains("roll")) 
            return "https://images.unsplash.com/photo-1528735602780-2552fd46c7af";
        if (n.contains("cake") || n.contains("jamun") || n.contains("rasgulla") || n.contains("kheer") || n.contains("rabdi") || n.contains("brownie") || n.contains("ice cream")) 
            return "https://images.unsplash.com/photo-1563729784474-d77dbb933a9e";
        if (n.contains("coffee") || n.contains("tea") || n.contains("drink") || n.contains("coke") || n.contains("pepsi") || n.contains("lassi") || n.contains("smoothie") || n.contains("shake")) 
            return "https://images.unsplash.com/photo-1544145945-f904253d0c7b";
        if (n.contains("pasta") || n.contains("lasagna")) 
            return "https://images.unsplash.com/photo-1473093226795-af9932fe5856";
        if (n.contains("salad")) 
            return "https://images.unsplash.com/photo-1512621776951-a57141f2eefd";
        
        return "https://images.unsplash.com/photo-1546069901-ba9599a7e63c"; // Default food image
    }

    private static String getRestaurantImage(String name) {
        String n = name.toLowerCase();
        
        // Check for popular brands and use local logos if available
        if (n.contains("domino")) return "android.resource://com.example.foodkart/drawable/dominos_logo";
        if (n.contains("kfc")) return "android.resource://com.example.foodkart/drawable/kfc_logo";
        if (n.contains("burger king")) return "android.resource://com.example.foodkart/drawable/burger_king_logo";
        if (n.contains("pizza hut")) return "android.resource://com.example.foodkart/drawable/pizza_hut_logo";
        if (n.contains("subway")) return "android.resource://com.example.foodkart/drawable/subway_logo";
        
        // McDonald's logo missing in resources, use high-quality generic burger image
        if (n.contains("mcdonald")) return "https://images.unsplash.com/photo-1568901346375-23c9450c58cd";
        
        // Specific mapping for previously hallucinated/missing links
        if (n.contains("urban tandoor") || n.contains("spice") || n.contains("tandoor")) 
            return "https://images.unsplash.com/photo-1585937421612-70a008356fbe"; // Indian Restaurant
        if (n.contains("ocean") || n.contains("delight")) 
            return "https://images.unsplash.com/photo-1551731388-f4265b3c2745"; // Seafood
        if (n.contains("street") || n.contains("treats") || n.contains("desi")) 
            return "https://images.unsplash.com/photo-1504674900247-0877df9cc836"; // Street Food
        if (n.contains("pizza") || n.contains("house")) 
            return "https://images.unsplash.com/photo-1513104890138-7c749659a591"; // Pizza
        
        // General categories for other restaurants
        if (n.contains("feast") || n.contains("maharaja") || n.contains("dining") || n.contains("elite") || n.contains("luxe")) 
            return "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4"; // Fine dine interior
        if (n.contains("cafe") || n.contains("leaf")) 
            return "https://images.unsplash.com/photo-1554118811-1e0d58224f24"; // Cafe
        if (n.contains("budget") || n.contains("bites") || n.contains("cravings") || n.contains("meals")) 
            return "https://images.unsplash.com/photo-1552566626-52f8b828add9"; // Casual dining
        
        return "https://images.unsplash.com/photo-1514933651103-005eec06c04b"; // Generic nice restaurant
    }

    public static List<Restaurant> getRestaurants() {
        List<Restaurant> list = new ArrayList<>();

        // Spice Junction
        Restaurant r1 = new Restaurant("r1", "Spice Junction", 250.0, 4.2, 120, 0.45, 3.2, new ArrayList<>(),
                getRestaurantImage("Spice Junction"), "Indian, North Indian", 32);
        r1.setMenu(getSpiceJunctionMenu());
        list.add(r1);

        // Budget Bites
        Restaurant r2 = new Restaurant("r2", "Budget Bites", 200.0, 4.0, 150, 0.3, 2.1, new ArrayList<>(),
                getRestaurantImage("Budget Bites"), "Fast Food, Thali", 25);
        r2.setMenu(getBudgetBitesMenu());
        list.add(r2);

        // Royal Feast
        Restaurant r3 = new Restaurant("r3", "Royal Feast", 1500.0, 4.8, 80, 0.1, 6.5, new ArrayList<>(),
                getRestaurantImage("Royal Feast"), "Fine Dine, Mughlai", 45);
        r3.setMenu(getRoyalFeastMenu());
        list.add(r3);

        // Urban Tandoor
        Restaurant r4 = new Restaurant("r4", "Urban Tandoor", 350.0, 4.3, 200, 0.2, 4.1, new ArrayList<>(),
                getRestaurantImage("Urban Tandoor"), "Tandoor, Indian", 35);
        r4.setMenu(getUrbanTandoorMenu());
        list.add(r4);

        // Street Treats
        Restaurant r5 = new Restaurant("r5", "Street Treats", 150.0, 4.1, 250, 0.5, 1.5, new ArrayList<>(),
                getRestaurantImage("Street Treats"), "Street Food, Snacks", 20);
        r5.setMenu(getStreetTreatsMenu());
        list.add(r5);

        // Golden Spoon
        Restaurant r6 = new Restaurant("r6", "Golden Spoon", 400.0, 3.8, 90, 0.6, 3.8, new ArrayList<>(),
                getRestaurantImage("Golden Spoon"), "Italian, Continental", 38);
        r6.setMenu(getGoldenSpoonMenu());
        list.add(r6);

        // Maharaja Dining
        Restaurant r7 = new Restaurant("r7", "Maharaja Dining", 1200.0, 4.1, 110, 0.25, 6.8, new ArrayList<>(),
                getRestaurantImage("Maharaja Dining"), "Royal Indian, Luxury", 50);
        r7.setMenu(getMaharajaDiningMenu());
        list.add(r7);

        // Green Leaf Café
        Restaurant r8 = new Restaurant("r8", "Green Leaf Café", 250.0, 3.5, 60, 0.7, 2.9, new ArrayList<>(),
                getRestaurantImage("Green Leaf Café"), "Healthy, Cafe", 28);
        r8.setMenu(getGreenLeafCafeMenu());
        list.add(r8);

        // Quick Cravings
        Restaurant r9 = new Restaurant("r9", "Quick Cravings", 300.0, 3.9, 300, 0.4, 2.3, new ArrayList<>(),
                getRestaurantImage("Quick Cravings"), "Fast Food, Pizza", 22);
        r9.setMenu(getQuickCravingsMenu());
        list.add(r9);

        // Ocean Delight
        Restaurant r10 = new Restaurant("r10", "Ocean Delight", 700.0, 4.1, 130, 0.35, 5.2, new ArrayList<>(),
                getRestaurantImage("Ocean Delight"), "Seafood, Coastal", 40);
        r10.setMenu(getOceanDelightMenu());
        list.add(r10);

        // Elite Dine
        Restaurant r11 = new Restaurant("r11", "Elite Dine", 1300.0, 4.5, 95, 0.2, 6.2, new ArrayList<>(),
                getRestaurantImage("Elite Dine"), "Premium, European", 48);
        r11.setMenu(getEliteDineMenu());
        list.add(r11);

        // Desi Zaika
        Restaurant r12 = new Restaurant("r12", "Desi Zaika", 250.0, 3.7, 180, 0.4, 3.0, new ArrayList<>(),
                getRestaurantImage("Desi Zaika"), "Home Style, Indian", 30);
        r12.setMenu(getDesiZaikaMenu());
        list.add(r12);

        // Pizza House
        Restaurant r13 = new Restaurant("r13", "Pizza House", 450.0, 4.3, 220, 0.3, 2.7, new ArrayList<>(),
                getRestaurantImage("Pizza House"), "Pizzas, Fast Food", 27);
        r13.setMenu(getPizzaHubMenu());
        list.add(r13);

        // Luxe Bites
        Restaurant r14 = new Restaurant("r14", "Luxe Bites", 1800.0, 4.8, 70, 0.15, 7.0, new ArrayList<>(),
                getRestaurantImage("Luxe Bites"), "Exotic, Luxury", 55);
        r14.setMenu(getLuxeBitesMenu());
        list.add(r14);

        // Daily Meals Corner
        Restaurant r15 = new Restaurant("r15", "Daily Meals Corner", 180.0, 4.0, 140, 0.35, 2.4, new ArrayList<>(),
                getRestaurantImage("Daily Meals Corner"), "Budget, Indian", 26);
        r15.setMenu(getDailyMealsMenu());
        list.add(r15);

        // KFC
        Restaurant r16 = new Restaurant("r16", "KFC", 450.0, 4.3, 500, 0.2, 3.5, new ArrayList<>(),
                getRestaurantImage("KFC"), "Fried Chicken, Burgers", 30);
        r16.setMenu(getKFCMenu());
        list.add(r16);

        // Pizza Hut
        Restaurant r17 = new Restaurant("r17", "Pizza Hut", 500.0, 4.2, 450, 0.25, 4.0, new ArrayList<>(),
                getRestaurantImage("Pizza Hut"), "Pizzas, Fast Food", 32);
        r17.setMenu(getPizzaHutMenu());
        list.add(r17);

        // Domino’s
        Restaurant r18 = new Restaurant("r18", "Domino’s", 400.0, 4.4, 600, 0.2, 3.2, new ArrayList<>(),
                getRestaurantImage("Domino’s"), "Pizzas, Fast Food", 28);
        r18.setMenu(getDominosMenu());
        list.add(r18);

        // Subway
        Restaurant r19 = new Restaurant("r19", "Subway", 350.0, 4.1, 400, 0.3, 2.8, new ArrayList<>(),
                getRestaurantImage("Subway"), "Sandwiches, Healthy", 25);
        r19.setMenu(getSubwayMenu());
        list.add(r19);

        // Burger King
        Restaurant r20 = new Restaurant("r20", "Burger King", 350.0, 4.2, 480, 0.25, 3.0, new ArrayList<>(),
                getRestaurantImage("Burger King"), "Burgers, Fast Food", 27);
        r20.setMenu(getBurgerKingMenu());
        list.add(r20);

        // McDonald’s
        Restaurant r21 = new Restaurant("r21", "McDonald’s", 300.0, 4.3, 550, 0.2, 2.5, new ArrayList<>(),
                getRestaurantImage("McDonald’s"), "Burgers, Fast Food", 26);
        r21.setMenu(getMcDonaldsMenu());
        list.add(r21);

        return list;
    }

    private static List<FoodItem> getSpiceJunctionMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("sj1", "Paneer Butter Masala", 249, "Rich and creamy paneer dish", getFoodImage("Paneer Butter Masala"), true));
        menu.add(new FoodItem("sj2", "Chicken Biryani", 299, "Aromatic chicken biryani", getFoodImage("Chicken Biryani"), false));
        menu.add(new FoodItem("sj3", "Veg Fried Rice", 199, "Delicious veg fried rice", getFoodImage("Veg Fried Rice"), true));
        menu.add(new FoodItem("sj4", "Chicken Tikka", 279, "Succulent chicken tikka", getFoodImage("Chicken Tikka"), false));
        menu.add(new FoodItem("sj5", "Dal Makhani", 199, "Creamy black lentils", getFoodImage("Dal Makhani"), true));
        menu.add(new FoodItem("sj6", "Butter Naan", 49, "Soft butter naan", getFoodImage("Butter Naan"), true));
        menu.add(new FoodItem("sj7", "Veg Manchurian", 189, "Spicy veg manchurian", getFoodImage("Veg Manchurian"), true));
        menu.add(new FoodItem("sj8", "Chicken Curry", 289, "Classic chicken curry", getFoodImage("Chicken Curry"), false));
        menu.add(new FoodItem("sj9", "Jeera Rice", 149, "Fragrant jeera rice", getFoodImage("Jeera Rice"), true));
        menu.add(new FoodItem("sj10", "Gulab Jamun", 99, "Sweet gulab jamun", getFoodImage("Gulab Jamun"), true));
        return menu;
    }

    private static List<FoodItem> getBudgetBitesMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("bb1", "Veg Thali", 199, "Complete veg thali", getFoodImage("Veg Thali"), true));
        menu.add(new FoodItem("bb2", "Chicken Thali", 299, "Complete chicken thali", getFoodImage("Chicken Thali"), false));
        menu.add(new FoodItem("bb3", "Paneer Roll", 149, "Spicy paneer roll", getFoodImage("Paneer Roll"), true));
        menu.add(new FoodItem("bb4", "Chicken Roll", 179, "Juicy chicken roll", getFoodImage("Chicken Roll"), false));
        menu.add(new FoodItem("bb5", "Veg Pulao", 199, "Healthy veg pulao", getFoodImage("Veg Pulao"), true));
        menu.add(new FoodItem("bb6", "Egg Curry", 159, "Classic egg curry", getFoodImage("Egg Curry"), false));
        menu.add(new FoodItem("bb7", "Dal Fry", 129, "Tempered yellow lentils", getFoodImage("Dal Fry"), true));
        menu.add(new FoodItem("bb8", "Chapati (2 pcs)", 49, "Soft whole wheat chapatis", getFoodImage("Chapati"), true));
        menu.add(new FoodItem("bb9", "Veg Burger", 119, "Crispy veg burger", getFoodImage("Veg Burger"), true));
        menu.add(new FoodItem("bb10", "Cold Coffee", 99, "Refreshing cold coffee", getFoodImage("Cold Coffee"), true));
        return menu;
    }

    private static List<FoodItem> getRoyalFeastMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("rf1", "Lobster Thermidor", 1299, "Creamy lobster thermidor", getFoodImage("Lobster Thermidor"), false));
        menu.add(new FoodItem("rf2", "Mutton Rogan Josh", 899, "Authentic kashmiri mutton", getFoodImage("Mutton Rogan Josh"), false));
        menu.add(new FoodItem("rf3", "Chicken Chettinad", 799, "Spicy south indian chicken", getFoodImage("Chicken Chettinad"), false));
        menu.add(new FoodItem("rf4", "Paneer Lababdar", 699, "Creamy paneer lababdar", getFoodImage("Paneer Lababdar"), true));
        menu.add(new FoodItem("rf5", "Fish Curry", 899, "Regional fish curry", getFoodImage("Fish Curry"), false));
        menu.add(new FoodItem("rf6", "Butter Garlic Prawns", 1099, "Sizzling butter garlic prawns", getFoodImage("Butter Garlic Prawns"), false));
        menu.add(new FoodItem("rf7", "Hyderabadi Biryani", 699, "Slow cooked hyderabadi biryani", getFoodImage("Hyderabadi Biryani"), false));
        menu.add(new FoodItem("rf8", "Malai Kofta", 599, "Creamy malai kofta", getFoodImage("Malai Kofta"), true));
        menu.add(new FoodItem("rf9", "Tandoori Platter", 999, "Assorted tandoori items", getFoodImage("Tandoori Platter"), false));
        menu.add(new FoodItem("rf10", "Shahi Tukda", 499, "Rich bread pudding", getFoodImage("Shahi Tukda"), true));
        return menu;
    }

    private static List<FoodItem> getUrbanTandoorMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ut1", "Paneer Tikka", 299, "", getFoodImage("Paneer Tikka"), true));
        menu.add(new FoodItem("ut2", "Chicken Tandoori", 399, "", getFoodImage("Chicken Tandoori"), false));
        menu.add(new FoodItem("ut3", "Veg Biryani", 249, "", getFoodImage("Veg Biryani"), true));
        menu.add(new FoodItem("ut4", "Mutton Curry", 499, "", getFoodImage("Mutton Curry"), false));
        menu.add(new FoodItem("ut5", "Dal Tadka", 199, "", getFoodImage("Dal Tadka"), true));
        menu.add(new FoodItem("ut6", "Butter Naan", 59, "", getFoodImage("Butter Naan"), true));
        menu.add(new FoodItem("ut7", "Garlic Naan", 79, "", getFoodImage("Garlic Naan"), true));
        menu.add(new FoodItem("ut8", "Chicken Masala", 349, "", getFoodImage("Chicken Masala"), false));
        menu.add(new FoodItem("ut9", "Veg Korma", 229, "", getFoodImage("Veg Korma"), true));
        menu.add(new FoodItem("ut10", "Rasgulla", 129, "", getFoodImage("Rasgulla"), true));
        return menu;
    }

    private static List<FoodItem> getStreetTreatsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("st1", "Pav Bhaji", 149, "", getFoodImage("Pav Bhaji"), true));
        menu.add(new FoodItem("st2", "Vada Pav (2 pcs)", 99, "", getFoodImage("Vada Pav"), true));
        menu.add(new FoodItem("st3", "Misal Pav", 129, "", getFoodImage("Misal Pav"), true));
        menu.add(new FoodItem("st4", "Veg Sandwich", 119, "", getFoodImage("Veg Sandwich"), true));
        menu.add(new FoodItem("st5", "Cheese Sandwich", 149, "", getFoodImage("Cheese Sandwich"), true));
        menu.add(new FoodItem("st6", "Masala Dosa", 129, "", getFoodImage("Masala Dosa"), true));
        menu.add(new FoodItem("st7", "Idli Sambar", 99, "", getFoodImage("Idli Sambar"), true));
        menu.add(new FoodItem("st8", "Samosa (2 pcs)", 79, "", getFoodImage("Samosa"), true));
        menu.add(new FoodItem("st9", "Chole Bhature", 179, "", getFoodImage("Chole Bhature"), true));
        menu.add(new FoodItem("st10", "Lassi", 99, "", getFoodImage("Lassi"), true));
        return menu;
    }

    private static List<FoodItem> getGoldenSpoonMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("gs1", "Chicken Alfredo Pasta", 399, "", getFoodImage("Chicken Alfredo Pasta"), false));
        menu.add(new FoodItem("gs2", "Veg Lasagna", 349, "", getFoodImage("Veg Lasagna"), true));
        menu.add(new FoodItem("gs3", "Margherita Pizza", 299, "", getFoodImage("Margherita Pizza"), true));
        menu.add(new FoodItem("gs4", "Pepperoni Pizza", 499, "", getFoodImage("Pepperoni Pizza"), false));
        menu.add(new FoodItem("gs5", "Garlic Bread", 199, "", getFoodImage("Garlic Bread"), true));
        menu.add(new FoodItem("gs6", "Caesar Salad", 249, "", getFoodImage("Caesar Salad"), true));
        menu.add(new FoodItem("gs7", "Grilled Chicken", 449, "", getFoodImage("Grilled Chicken"), false));
        menu.add(new FoodItem("gs8", "Mushroom Soup", 199, "", getFoodImage("Mushroom Soup"), true));
        menu.add(new FoodItem("gs9", "Chocolate Cake", 249, "", getFoodImage("Chocolate Cake"), true));
        menu.add(new FoodItem("gs10", "Cold Coffee", 179, "", getFoodImage("Cold Coffee"), true));
        return menu;
    }

    private static List<FoodItem> getMaharajaDiningMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("md1", "Royal Thali", 1499, "", getFoodImage("Royal Thali"), true));
        menu.add(new FoodItem("md2", "Mutton Biryani", 999, "", getFoodImage("Mutton Biryani"), false));
        menu.add(new FoodItem("md3", "Butter Chicken", 899, "", getFoodImage("Butter Chicken"), false));
        menu.add(new FoodItem("md4", "Paneer Royal", 799, "", getFoodImage("Paneer Royal"), true));
        menu.add(new FoodItem("md5", "Chicken Seekh Kebab", 899, "", getFoodImage("Chicken Seekh Kebab"), false));
        menu.add(new FoodItem("md6", "Fish Fry", 999, "", getFoodImage("Fish Fry"), false));
        menu.add(new FoodItem("md7", "Dal Maharani", 699, "", getFoodImage("Dal Maharani"), true));
        menu.add(new FoodItem("md8", "Stuffed Naan", 199, "", getFoodImage("Stuffed Naan"), true));
        menu.add(new FoodItem("md9", "Shahi Paneer", 799, "", getFoodImage("Shahi Paneer"), true));
        menu.add(new FoodItem("md10", "Rabdi", 499, "", getFoodImage("Rabdi"), true));
        return menu;
    }

    private static List<FoodItem> getGreenLeafCafeMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("glc1", "Veg Salad Bowl", 199, "", getFoodImage("Veg Salad Bowl"), true));
        menu.add(new FoodItem("glc2", "Paneer Wrap", 249, "", getFoodImage("Paneer Wrap"), true));
        menu.add(new FoodItem("glc3", "Veg Burger", 199, "", getFoodImage("Veg Burger"), true));
        menu.add(new FoodItem("glc4", "Pasta Primavera", 299, "", getFoodImage("Pasta Primavera"), true));
        menu.add(new FoodItem("glc5", "Veg Soup", 149, "", getFoodImage("Veg Soup"), true));
        menu.add(new FoodItem("glc6", "Grilled Sandwich", 199, "", getFoodImage("Grilled Sandwich"), true));
        menu.add(new FoodItem("glc7", "Smoothie", 179, "", getFoodImage("Smoothie"), true));
        menu.add(new FoodItem("glc8", "Brownie", 199, "", getFoodImage("Brownie"), true));
        menu.add(new FoodItem("glc9", "Garlic Bread", 149, "", getFoodImage("Garlic Bread"), true));
        menu.add(new FoodItem("glc10", "Iced Tea", 129, "", getFoodImage("Iced Tea"), true));
        return menu;
    }

    private static List<FoodItem> getQuickCravingsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("qc1", "Chicken Burger", 199, "", getFoodImage("Chicken Burger"), false));
        menu.add(new FoodItem("qc2", "Veg Burger", 149, "", getFoodImage("Veg Burger"), true));
        menu.add(new FoodItem("qc3", "French Fries", 129, "", getFoodImage("French Fries"), true));
        menu.add(new FoodItem("qc4", "Chicken Nuggets", 199, "", getFoodImage("Chicken Nuggets"), false));
        menu.add(new FoodItem("qc5", "Veg Pizza", 249, "", getFoodImage("Veg Pizza"), true));
        menu.add(new FoodItem("qc6", "Chicken Pizza", 299, "", getFoodImage("Chicken Pizza"), false));
        menu.add(new FoodItem("qc7", "Cold Drink", 99, "", getFoodImage("Cold Drink"), true));
        menu.add(new FoodItem("qc8", "Milkshake", 149, "", getFoodImage("Milkshake"), true));
        menu.add(new FoodItem("qc9", "Hot Dog", 179, "", getFoodImage("Hot Dog"), false));
        menu.add(new FoodItem("qc10", "Cheese Fries", 159, "", getFoodImage("Cheese Fries"), true));
        return menu;
    }

    private static List<FoodItem> getOceanDelightMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("od1", "Grilled Fish", 599, "", getFoodImage("Grilled Fish"), false));
        menu.add(new FoodItem("od2", "Prawn Curry", 699, "", getFoodImage("Prawn Curry"), false));
        menu.add(new FoodItem("od3", "Fish Biryani", 499, "", getFoodImage("Fish Biryani"), false));
        menu.add(new FoodItem("od4", "Crab Masala", 799, "", getFoodImage("Crab Masala"), false));
        menu.add(new FoodItem("od5", "Butter Garlic Fish", 649, "", getFoodImage("Butter Garlic Fish"), false));
        menu.add(new FoodItem("od6", "Rice", 199, "", getFoodImage("Rice"), true));
        menu.add(new FoodItem("od7", "Fish Fry", 499, "", getFoodImage("Fish Fry"), false));
        menu.add(new FoodItem("od8", "Veg Salad", 199, "", getFoodImage("Veg Salad"), true));
        menu.add(new FoodItem("od9", "Soup", 249, "", getFoodImage("Soup"), true));
        menu.add(new FoodItem("od10", "Ice Cream", 199, "", getFoodImage("Ice Cream"), true));
        return menu;
    }

    private static List<FoodItem> getEliteDineMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ed1", "Steak", 1299, "", getFoodImage("Steak"), false));
        menu.add(new FoodItem("ed2", "Grilled Salmon", 1199, "", getFoodImage("Grilled Salmon"), false));
        menu.add(new FoodItem("ed3", "Chicken Steak", 899, "", getFoodImage("Chicken Steak"), false));
        menu.add(new FoodItem("ed4", "Veg Steak", 799, "", getFoodImage("Veg Steak"), true));
        menu.add(new FoodItem("ed5", "Pasta Alfredo", 699, "", getFoodImage("Pasta Alfredo"), true));
        menu.add(new FoodItem("ed6", "Risotto", 899, "", getFoodImage("Risotto"), true));
        menu.add(new FoodItem("ed7", "Soup", 399, "", getFoodImage("Soup"), true));
        menu.add(new FoodItem("ed8", "Salad", 499, "", getFoodImage("Salad"), true));
        menu.add(new FoodItem("ed9", "Cheesecake", 599, "", getFoodImage("Cheesecake"), true));
        menu.add(new FoodItem("ed10", "Mocktail", 399, "", getFoodImage("Mocktail"), true));
        return menu;
    }

    private static List<FoodItem> getDesiZaikaMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dz1", "Rajma Chawal", 199, "", getFoodImage("Rajma Chawal"), true));
        menu.add(new FoodItem("dz2", "Chole Chawal", 199, "", getFoodImage("Chole Chawal"), true));
        menu.add(new FoodItem("dz3", "Paneer Masala", 249, "", getFoodImage("Paneer Masala"), true));
        menu.add(new FoodItem("dz4", "Chicken Curry", 299, "", getFoodImage("Chicken Curry"), false));
        menu.add(new FoodItem("dz5", "Dal Fry", 149, "", getFoodImage("Dal Fry"), true));
        menu.add(new FoodItem("dz6", "Chapati", 49, "", getFoodImage("Chapati"), true));
        menu.add(new FoodItem("dz7", "Jeera Rice", 149, "", getFoodImage("Jeera Rice"), true));
        menu.add(new FoodItem("dz8", "Aloo Gobi", 199, "", getFoodImage("Aloo Gobi"), true));
        menu.add(new FoodItem("dz9", "Kheer", 129, "", getFoodImage("Kheer"), true));
        menu.add(new FoodItem("dz10", "Lassi", 99, "", getFoodImage("Lassi"), true));
        return menu;
    }

    private static List<FoodItem> getPizzaHubMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("ph1", "Margherita", 249, "", getFoodImage("Margherita"), true));
        menu.add(new FoodItem("ph2", "Farmhouse", 349, "", getFoodImage("Farmhouse"), true));
        menu.add(new FoodItem("ph3", "Pepperoni", 399, "", getFoodImage("Pepperoni"), false));
        menu.add(new FoodItem("ph4", "BBQ Chicken", 449, "", getFoodImage("BBQ Chicken"), false));
        menu.add(new FoodItem("ph5", "Veg Extravaganza", 379, "", getFoodImage("Veg Extravaganza"), true));
        menu.add(new FoodItem("ph6", "Garlic Bread", 199, "", getFoodImage("Garlic Bread"), true));
        menu.add(new FoodItem("ph7", "Cheese Burst Pizza", 499, "", getFoodImage("Cheese Burst Pizza"), true));
        menu.add(new FoodItem("ph8", "Pasta", 299, "", getFoodImage("Pasta"), true));
        menu.add(new FoodItem("ph9", "Choco Lava Cake", 149, "", getFoodImage("Choco Lava Cake"), true));
        menu.add(new FoodItem("ph10", "Cold Drink", 99, "", getFoodImage("Cold Drink"), true));
        return menu;
    }

    private static List<FoodItem> getLuxeBitesMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("lb1", "Wagyu Steak", 1499, "", getFoodImage("Wagyu Steak"), false));
        menu.add(new FoodItem("lb2", "Lobster Grill", 1399, "", getFoodImage("Lobster Grill"), false));
        menu.add(new FoodItem("lb3", "Truffle Pasta", 1099, "", getFoodImage("Truffle Pasta"), true));
        menu.add(new FoodItem("lb4", "Premium Sushi Platter", 1299, "", getFoodImage("Premium Sushi Platter"), false));
        menu.add(new FoodItem("lb5", "Caviar Salad", 1499, "", getFoodImage("Caviar Salad"), false));
        menu.add(new FoodItem("lb6", "Grilled Chicken Deluxe", 999, "", getFoodImage("Grilled Chicken Deluxe"), false));
        menu.add(new FoodItem("lb7", "Exotic Veg Platter", 899, "", getFoodImage("Exotic Veg Platter"), true));
        menu.add(new FoodItem("lb8", "Cheese Board", 799, "", getFoodImage("Cheese Board"), true));
        menu.add(new FoodItem("lb9", "Tiramisu", 699, "", getFoodImage("Tiramisu"), true));
        menu.add(new FoodItem("lb10", "Signature Cocktail", 599, "", getFoodImage("Signature Cocktail"), true));
        return menu;
    }

    private static List<FoodItem> getDailyMealsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dm1", "Veg Thali", 179, "", getFoodImage("Veg Thali"), true));
        menu.add(new FoodItem("dm2", "Chicken Thali", 299, "", getFoodImage("Chicken Thali"), false));
        menu.add(new FoodItem("dm3", "Egg Curry", 149, "", getFoodImage("Egg Curry"), false));
        menu.add(new FoodItem("dm4", "Dal Rice", 129, "", getFoodImage("Dal Rice"), true));
        menu.add(new FoodItem("dm5", "Paneer Curry", 249, "", getFoodImage("Paneer Curry"), true));
        menu.add(new FoodItem("dm6", "Chapati", 49, "", getFoodImage("Chapati"), true));
        menu.add(new FoodItem("dm7", "Veg Pulao", 199, "", getFoodImage("Veg Pulao"), true));
        menu.add(new FoodItem("dm8", "Chicken Curry", 299, "", getFoodImage("Chicken Curry"), false));
        menu.add(new FoodItem("dm9", "Curd Rice", 149, "", getFoodImage("Curd Rice"), true));
        menu.add(new FoodItem("dm10", "Gulab Jamun", 99, "", getFoodImage("Gulab Jamun"), true));
        return menu;
    }

    private static List<FoodItem> getKFCMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("kfc1", "Zinger Burger", 219, "", getFoodImage("Zinger Burger"), false));
        menu.add(new FoodItem("kfc2", "Hot Wings (6 pcs)", 299, "", getFoodImage("Hot Wings"), false));
        menu.add(new FoodItem("kfc3", "Chicken Bucket (10 pcs)", 699, "", getFoodImage("Chicken Bucket"), false));
        menu.add(new FoodItem("kfc4", "Popcorn Chicken", 249, "", getFoodImage("Popcorn Chicken"), false));
        menu.add(new FoodItem("kfc5", "Chicken Strips (3 pcs)", 199, "", getFoodImage("Chicken Strips"), false));
        menu.add(new FoodItem("kfc6", "Veg Zinger", 199, "", getFoodImage("Veg Zinger"), true));
        menu.add(new FoodItem("kfc7", "French Fries", 129, "", getFoodImage("French Fries"), true));
        menu.add(new FoodItem("kfc8", "Chicken Roll", 179, "", getFoodImage("Chicken Roll"), false));
        menu.add(new FoodItem("kfc9", "Krusher Drink", 149, "", getFoodImage("Krusher Drink"), true));
        menu.add(new FoodItem("kfc10", "Chocolate Lava Cake", 109, "", getFoodImage("Chocolate Lava Cake"), true));
        return menu;
    }

    private static List<FoodItem> getPizzaHutMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("phu1", "Margherita Pizza", 249, "", getFoodImage("Margherita Pizza"), true));
        menu.add(new FoodItem("phu2", "Veggie Supreme", 399, "", getFoodImage("Veggie Supreme"), true));
        menu.add(new FoodItem("phu3", "Chicken Supreme", 499, "", getFoodImage("Chicken Supreme"), false));
        menu.add(new FoodItem("phu4", "Paneer Tikka Pizza", 449, "", getFoodImage("Paneer Tikka Pizza"), true));
        menu.add(new FoodItem("phu5", "Garlic Bread", 199, "", getFoodImage("Garlic Bread"), true));
        menu.add(new FoodItem("phu6", "Cheese Garlic Bread", 249, "", getFoodImage("Cheese Garlic Bread"), true));
        menu.add(new FoodItem("phu7", "Veg Pasta", 299, "", getFoodImage("Veg Pasta"), true));
        menu.add(new FoodItem("phu8", "Chicken Pasta", 349, "", getFoodImage("Chicken Pasta"), false));
        menu.add(new FoodItem("phu9", "Choco Lava Cake", 149, "", getFoodImage("Choco Lava Cake"), true));
        menu.add(new FoodItem("phu10", "Pepsi", 99, "", getFoodImage("Pepsi"), true));
        return menu;
    }

    private static List<FoodItem> getDominosMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("dom1", "Margherita Pizza", 199, "", getFoodImage("Margherita Pizza"), true));
        menu.add(new FoodItem("dom2", "Farmhouse Pizza", 349, "", getFoodImage("Farmhouse Pizza"), true));
        menu.add(new FoodItem("dom3", "Peppy Paneer Pizza", 379, "", getFoodImage("Peppy Paneer Pizza"), true));
        menu.add(new FoodItem("dom4", "Chicken Dominator", 599, "", getFoodImage("Chicken Dominator"), false));
        menu.add(new FoodItem("dom5", "Veg Extravaganza", 399, "", getFoodImage("Veg Extravaganza"), true));
        menu.add(new FoodItem("dom6", "Garlic Breadsticks", 199, "", getFoodImage("Garlic Breadsticks"), true));
        menu.add(new FoodItem("dom7", "Stuffed Garlic Bread", 249, "", getFoodImage("Stuffed Garlic Bread"), true));
        menu.add(new FoodItem("dom8", "Pasta Italiano Veg", 299, "", getFoodImage("Pasta Italiano Veg"), true));
        menu.add(new FoodItem("dom9", "Choco Lava Cake", 149, "", getFoodImage("Choco Lava Cake"), true));
        menu.add(new FoodItem("dom10", "Coke", 99, "", getFoodImage("Coke"), true));
        return menu;
    }

    private static List<FoodItem> getSubwayMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("sub1", "Veggie Delight Sub", 199, "", getFoodImage("Veggie Delight Sub"), true));
        menu.add(new FoodItem("sub2", "Paneer Tikka Sub", 249, "", getFoodImage("Paneer Tikka Sub"), true));
        menu.add(new FoodItem("sub3", "Chicken Teriyaki Sub", 299, "", getFoodImage("Chicken Teriyaki Sub"), false));
        menu.add(new FoodItem("sub4", "Chicken Seekh Sub", 279, "", getFoodImage("Chicken Seekh Sub"), false));
        menu.add(new FoodItem("sub5", "Aloo Patty Sub", 179, "", getFoodImage("Aloo Patty Sub"), true));
        menu.add(new FoodItem("sub6", "Tuna Sub", 349, "", getFoodImage("Tuna Sub"), false));
        menu.add(new FoodItem("sub7", "Salad Bowl", 199, "", getFoodImage("Salad Bowl"), true));
        menu.add(new FoodItem("sub8", "Cookies (2 pcs)", 129, "", getFoodImage("Cookies"), true));
        menu.add(new FoodItem("sub9", "Cold Drink", 99, "", getFoodImage("Cold Drink"), true));
        menu.add(new FoodItem("sub10", "Wrap", 249, "", getFoodImage("Wrap"), true));
        return menu;
    }

    private static List<FoodItem> getBurgerKingMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("bk1", "Veg Whopper", 179, "", getFoodImage("Veg Whopper"), true));
        menu.add(new FoodItem("bk2", "Chicken Whopper", 219, "", getFoodImage("Chicken Whopper"), false));
        menu.add(new FoodItem("bk3", "Crispy Veg Burger", 129, "", getFoodImage("Crispy Veg Burger"), true));
        menu.add(new FoodItem("bk4", "Chicken Crispy Burger", 149, "", getFoodImage("Chicken Crispy Burger"), false));
        menu.add(new FoodItem("bk5", "French Fries", 129, "", getFoodImage("French Fries"), true));
        menu.add(new FoodItem("bk6", "Veg Nuggets", 149, "", getFoodImage("Veg Nuggets"), true));
        menu.add(new FoodItem("bk7", "Chicken Nuggets", 179, "", getFoodImage("Chicken Nuggets"), false));
        menu.add(new FoodItem("bk8", "Chocolate Shake", 179, "", getFoodImage("Chocolate Shake"), true));
        menu.add(new FoodItem("bk9", "Cold Drink", 99, "", getFoodImage("Cold Drink"), true));
        menu.add(new FoodItem("bk10", "Cheese Burger", 159, "", getFoodImage("Cheese Burger"), true));
        return menu;
    }

    private static List<FoodItem> getMcDonaldsMenu() {
        List<FoodItem> menu = new ArrayList<>();
        menu.add(new FoodItem("mc1", "McAloo Tikki Burger", 99, "", getFoodImage("McAloo Tikki Burger"), true));
        menu.add(new FoodItem("mc2", "McChicken Burger", 149, "", getFoodImage("McChicken Burger"), false));
        menu.add(new FoodItem("mc3", "Filet-O-Fish", 199, "", getFoodImage("Filet-O-Fish"), false));
        menu.add(new FoodItem("mc4", "McVeggie Burger", 149, "", getFoodImage("McVeggie Burger"), true));
        menu.add(new FoodItem("mc5", "Chicken McNuggets (6 pcs)", 199, "", getFoodImage("Chicken McNuggets"), false));
        menu.add(new FoodItem("mc6", "French Fries", 129, "", getFoodImage("French Fries"), true));
        menu.add(new FoodItem("mc7", "Veg Pizza McPuff", 49, "", getFoodImage("Veg Pizza McPuff"), true));
        menu.add(new FoodItem("mc8", "McFlurry", 129, "", getFoodImage("McFlurry"), true));
        menu.add(new FoodItem("mc9", "Cold Coffee", 149, "", getFoodImage("Cold Coffee"), true));
        menu.add(new FoodItem("mc10", "Coke", 99, "", getFoodImage("Coke"), true));
        return menu;
    }
}
