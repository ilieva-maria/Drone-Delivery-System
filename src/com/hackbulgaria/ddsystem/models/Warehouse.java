package com.hackbulgaria.ddsystem.models;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private Coordinates coordinates;
    private int id;
    private String name;
    private List<StockItem> items;

    public Warehouse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse(int id, Coordinates coordinates, String name) {
        this.setCoordinates(coordinates);
        this.setId(id);
        this.name = name;
        this.items = new ArrayList<>();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void add(StockItem stockItem){
        items.add(stockItem);
    }

    public List<StockItem> getItems() {
        return items;
    }
}
