package com.hackbulgaria.ddsystem.models;

public class Coordinates {
    private double x;
    private double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(Coordinates a, Coordinates b) {
        return (a.x - b.x) * (a.x - b.x) +
                (a.y - b.y) * (a.y - b.y);
    }
}
