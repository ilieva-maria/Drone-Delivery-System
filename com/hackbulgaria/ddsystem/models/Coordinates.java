package com.hackbulgaria.ddsystem.models;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int distance(Coordinates a, Coordinates b) {
        return (a.x - b.x) * (a.x - b.x) +
                (a.y - b.y) * (a.y - b.y);
    }
}
