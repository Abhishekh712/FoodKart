package com.example.foodkart;

public class UserInteraction {
    public enum Type { CLICK, ORDER }

    private Type type;
    private long timestamp;

    public UserInteraction(Type type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
    }

    public Type getType() { return type; }
    public long getTimestamp() { return timestamp; }
}