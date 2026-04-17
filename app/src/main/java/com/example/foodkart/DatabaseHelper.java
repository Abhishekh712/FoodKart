package com.example.foodkart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodKart.db";
    private static final int DATABASE_VERSION = 3;

    // User Table
    public static final String TABLE_USERS = "users";
    public static final String COL_USER_EMAIL = "email";
    public static final String COL_USER_NAME = "name";
    public static final String COL_USER_PASSWORD = "password";
    public static final String COL_USER_PHONE = "phone";
    public static final String COL_USER_ADDRESS = "address";

    // Cart Table
    public static final String TABLE_CART = "cart";
    public static final String COL_CART_ID = "id";
    public static final String COL_CART_USER_EMAIL = "user_email";
    public static final String COL_CART_FOOD_ID = "food_id";
    public static final String COL_CART_FOOD_NAME = "food_name";
    public static final String COL_CART_FOOD_PRICE = "food_price";
    public static final String COL_CART_QUANTITY = "quantity";
    public static final String COL_CART_IMAGE_URL = "image_url";

    // Restaurant Table
    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String COL_REST_ID = "id";
    public static final String COL_REST_NAME = "name";
    public static final String COL_REST_PRICE_TWO = "price_for_two";
    public static final String COL_REST_RATING = "rating";
    public static final String COL_REST_REVIEWS = "review_count";
    public static final String COL_REST_VARIANCE = "variance";
    public static final String COL_REST_DISTANCE = "distance";
    public static final String COL_REST_IMAGE = "image_url";
    public static final String COL_REST_CUISINE = "cuisine";
    public static final String COL_REST_TIME = "delivery_time";

    // Food Item Table
    public static final String TABLE_FOOD = "food_items";
    public static final String COL_FOOD_ID = "id";
    public static final String COL_FOOD_REST_ID = "restaurant_id";
    public static final String COL_FOOD_NAME = "name";
    public static final String COL_FOOD_PRICE = "price";
    public static final String COL_FOOD_DESC = "description";
    public static final String COL_FOOD_IMAGE = "image_url";
    public static final String COL_FOOD_IS_VEG = "is_veg";

    // Orders Table
    public static final String TABLE_ORDERS = "orders";
    public static final String COL_ORDER_ID = "id";
    public static final String COL_ORDER_USER_EMAIL = "user_email";
    public static final String COL_ORDER_DATE = "order_date";
    public static final String COL_ORDER_TOTAL = "total_amount";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_EMAIL + " TEXT PRIMARY KEY, " +
                COL_USER_NAME + " TEXT, " +
                COL_USER_PASSWORD + " TEXT, " +
                COL_USER_PHONE + " TEXT, " +
                COL_USER_ADDRESS + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_CART + " (" +
                COL_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_CART_USER_EMAIL + " TEXT, " +
                COL_CART_FOOD_ID + " TEXT, " +
                COL_CART_FOOD_NAME + " TEXT, " +
                COL_CART_FOOD_PRICE + " REAL, " +
                COL_CART_QUANTITY + " INTEGER, " +
                COL_CART_IMAGE_URL + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_RESTAURANTS + " (" +
                COL_REST_ID + " TEXT PRIMARY KEY, " +
                COL_REST_NAME + " TEXT, " +
                COL_REST_PRICE_TWO + " REAL, " +
                COL_REST_RATING + " REAL, " +
                COL_REST_REVIEWS + " INTEGER, " +
                COL_REST_VARIANCE + " REAL, " +
                COL_REST_DISTANCE + " REAL, " +
                COL_REST_IMAGE + " TEXT, " +
                COL_REST_CUISINE + " TEXT, " +
                COL_REST_TIME + " INTEGER)");

        db.execSQL("CREATE TABLE " + TABLE_FOOD + " (" +
                COL_FOOD_ID + " TEXT PRIMARY KEY, " +
                COL_FOOD_REST_ID + " TEXT, " +
                COL_FOOD_NAME + " TEXT, " +
                COL_FOOD_PRICE + " REAL, " +
                COL_FOOD_DESC + " TEXT, " +
                COL_FOOD_IMAGE + " TEXT, " +
                COL_FOOD_IS_VEG + " INTEGER)");

        db.execSQL("CREATE TABLE " + TABLE_ORDERS + " (" +
                COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_ORDER_USER_EMAIL + " TEXT, " +
                COL_ORDER_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                COL_ORDER_TOTAL + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RESTAURANTS + " (" +
                    COL_REST_ID + " TEXT PRIMARY KEY, " +
                    COL_REST_NAME + " TEXT, " +
                    COL_REST_PRICE_TWO + " REAL, " +
                    COL_REST_RATING + " REAL, " +
                    COL_REST_REVIEWS + " INTEGER, " +
                    COL_REST_VARIANCE + " REAL, " +
                    COL_REST_DISTANCE + " REAL, " +
                    COL_REST_IMAGE + " TEXT, " +
                    COL_REST_CUISINE + " TEXT, " +
                    COL_REST_TIME + " INTEGER)");

            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FOOD + " (" +
                    COL_FOOD_ID + " TEXT PRIMARY KEY, " +
                    COL_FOOD_REST_ID + " TEXT, " +
                    COL_FOOD_NAME + " TEXT, " +
                    COL_FOOD_PRICE + " REAL, " +
                    COL_FOOD_DESC + " TEXT, " +
                    COL_FOOD_IMAGE + " TEXT, " +
                    COL_FOOD_IS_VEG + " INTEGER)");
        }
        if (oldVersion < 3) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS + " (" +
                    COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ORDER_USER_EMAIL + " TEXT, " +
                    COL_ORDER_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    COL_ORDER_TOTAL + " REAL)");
        }
    }

    // --- SEEDING LOGIC ---
    public void seedDatabaseIfEmpty() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_RESTAURANTS, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();

        if (count == 0) {
            List<Restaurant> mockList = MockData.getRestaurants();
            for (Restaurant r : mockList) {
                addRestaurant(r);
                for (FoodItem f : r.getMenu()) {
                    addFoodItem(f, r.getId());
                }
            }
        }
    }

    private void addRestaurant(Restaurant r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_REST_ID, r.getId());
        v.put(COL_REST_NAME, r.getName());
        v.put(COL_REST_PRICE_TWO, r.getPriceInINR());
        v.put(COL_REST_RATING, r.getAverageRating());
        v.put(COL_REST_REVIEWS, r.getReviewCount());
        v.put(COL_REST_VARIANCE, r.getRatingVariance());
        v.put(COL_REST_DISTANCE, r.getDistanceInKm());
        v.put(COL_REST_IMAGE, r.getImageUrl());
        v.put(COL_REST_CUISINE, r.getCuisine());
        v.put(COL_REST_TIME, r.getDeliveryTimeMin());
        db.insert(TABLE_RESTAURANTS, null, v);
    }

    private void addFoodItem(FoodItem f, String restId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_FOOD_ID, f.getId());
        v.put(COL_FOOD_REST_ID, restId);
        v.put(COL_FOOD_NAME, f.getName());
        v.put(COL_FOOD_PRICE, f.getPrice());
        v.put(COL_FOOD_DESC, f.getDescription());
        v.put(COL_FOOD_IMAGE, f.getImageUrl());
        v.put(COL_FOOD_IS_VEG, f.isVeg() ? 1 : 0);
        db.insert(TABLE_FOOD, null, v);
    }

    // --- QUERY LOGIC ---
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_RESTAURANTS, null);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(c.getColumnIndexOrThrow(COL_REST_ID));
                Restaurant r = new Restaurant(
                    id,
                    c.getString(c.getColumnIndexOrThrow(COL_REST_NAME)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_REST_PRICE_TWO)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_REST_RATING)),
                    c.getInt(c.getColumnIndexOrThrow(COL_REST_REVIEWS)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_REST_VARIANCE)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_REST_DISTANCE)),
                    new ArrayList<>(),
                    c.getString(c.getColumnIndexOrThrow(COL_REST_IMAGE)),
                    c.getString(c.getColumnIndexOrThrow(COL_REST_CUISINE)),
                    c.getInt(c.getColumnIndexOrThrow(COL_REST_TIME))
                );
                r.setMenu(getFoodForRestaurant(id));
                list.add(r);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public List<FoodItem> getFoodForRestaurant(String restId) {
        List<FoodItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_FOOD + " WHERE " + COL_FOOD_REST_ID + "=?", new String[]{restId});

        if (c.moveToFirst()) {
            do {
                list.add(new FoodItem(
                    c.getString(c.getColumnIndexOrThrow(COL_FOOD_ID)),
                    c.getString(c.getColumnIndexOrThrow(COL_FOOD_NAME)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_FOOD_PRICE)),
                    c.getString(c.getColumnIndexOrThrow(COL_FOOD_DESC)),
                    c.getString(c.getColumnIndexOrThrow(COL_FOOD_IMAGE)),
                    c.getInt(c.getColumnIndexOrThrow(COL_FOOD_IS_VEG)) == 1
                ));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // Cart operations
    public void addToCart(String userEmail, FoodItem food) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if item already exists in cart for this user
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " + COL_CART_USER_EMAIL + "=? AND " + COL_CART_FOOD_ID + "=?", new String[]{userEmail, food.getId()});
        
        if (cursor.moveToFirst()) {
            int currentQty = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CART_QUANTITY));
            ContentValues v = new ContentValues();
            v.put(COL_CART_QUANTITY, currentQty + 1);
            db.update(TABLE_CART, v, COL_CART_USER_EMAIL + "=? AND " + COL_CART_FOOD_ID + "=?", new String[]{userEmail, food.getId()});
        } else {
            ContentValues v = new ContentValues();
            v.put(COL_CART_USER_EMAIL, userEmail);
            v.put(COL_CART_FOOD_ID, food.getId());
            v.put(COL_CART_FOOD_NAME, food.getName());
            v.put(COL_CART_FOOD_PRICE, food.getPrice());
            v.put(COL_CART_QUANTITY, 1);
            v.put(COL_CART_IMAGE_URL, food.getImageUrl());
            db.insert(TABLE_CART, null, v);
        }
        cursor.close();
    }

    public void removeFromCart(String userEmail, String foodId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " + COL_CART_USER_EMAIL + "=? AND " + COL_CART_FOOD_ID + "=?", new String[]{userEmail, foodId});
        
        if (cursor.moveToFirst()) {
            int currentQty = cursor.getInt(cursor.getColumnIndexOrThrow(COL_CART_QUANTITY));
            if (currentQty > 1) {
                ContentValues v = new ContentValues();
                v.put(COL_CART_QUANTITY, currentQty - 1);
                db.update(TABLE_CART, v, COL_CART_USER_EMAIL + "=? AND " + COL_CART_FOOD_ID + "=?", new String[]{userEmail, foodId});
            } else {
                db.delete(TABLE_CART, COL_CART_USER_EMAIL + "=? AND " + COL_CART_FOOD_ID + "=?", new String[]{userEmail, foodId});
            }
        }
        cursor.close();
    }

    public List<CartItem> getCartItems(String userEmail) {
        List<CartItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_CART + " WHERE " + COL_CART_USER_EMAIL + "=?", new String[]{userEmail});

        if (c.moveToFirst()) {
            do {
                FoodItem f = new FoodItem(
                    c.getString(c.getColumnIndexOrThrow(COL_CART_FOOD_ID)),
                    c.getString(c.getColumnIndexOrThrow(COL_CART_FOOD_NAME)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_CART_FOOD_PRICE)),
                    "", // description not needed in cart
                    c.getString(c.getColumnIndexOrThrow(COL_CART_IMAGE_URL)),
                    true // isVeg placeholder
                );
                list.add(new CartItem(f, c.getInt(c.getColumnIndexOrThrow(COL_CART_QUANTITY))));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public void clearCart(String userEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART, COL_CART_USER_EMAIL + "=?", new String[]{userEmail});
    }

    // Order operations
    public void addOrder(String userEmail, double total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COL_ORDER_USER_EMAIL, userEmail);
        v.put(COL_ORDER_TOTAL, total);
        db.insert(TABLE_ORDERS, null, v);
    }

    // User operations
    public boolean addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, name);
        values.put(COL_USER_EMAIL, email);
        values.put(COL_USER_PASSWORD, password);
        long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_USER_EMAIL + "=? AND " + COL_USER_PASSWORD + "=?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Cursor getUserData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COL_USER_EMAIL + "=?", new String[]{email});
    }

    public boolean updatePassword(String email, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_PASSWORD, newPassword);
        int result = db.update(TABLE_USERS, values, COL_USER_EMAIL + "=?", new String[]{email});
        return result > 0;
    }

    public boolean updateProfile(String email, String name, String phone, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_NAME, name);
        values.put(COL_USER_PHONE, phone);
        values.put(COL_USER_ADDRESS, address);
        int result = db.update(TABLE_USERS, values, COL_USER_EMAIL + "=?", new String[]{email});
        return result > 0;
    }
}
