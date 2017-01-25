package com.hackbulgaria.ddsystem.models;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private int id;
    private long timestamp;
    private Coordinates coordinates;
    private Map<Product, Integer> products = new HashMap<>();

    public Request() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return timestamp;
    }

    public void setTime(long time) {
        this.timestamp = time;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}
