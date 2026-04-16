package com.example.foodkart;

public class FuzzyNumber {
    private double l; // lower
    private double m; // middle
    private double u; // upper

    public FuzzyNumber(double l, double m, double u) {
        this.l = l;
        this.m = m;
        this.u = u;
    }

    public double getL() { return l; }
    public double getM() { return m; }
    public double getU() { return u; }
}