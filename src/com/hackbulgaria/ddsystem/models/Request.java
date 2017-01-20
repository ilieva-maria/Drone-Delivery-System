package com.hackbulgaria.ddsystem.models;

import java.sql.Time;
import java.util.List;

public class Request {
    private int id;
    private Time time;
    private Coordinates coordinates;
    private List<StockItem> products;

    public Request() {
        // TODO Auto-generated constructor stub
    }

    public Request(int id, Time time, Coordinates coordinates,
                   List<StockItem> products) {
        this.id = id;
        this.time = time;
        this.coordinates = coordinates;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<StockItem> getProducts() {
        return products;
    }

    public void setProducts(List<StockItem> products) {
        this.products = products;
    }
}
