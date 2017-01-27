package com.hackbulgaria.ddsystem.models;

import java.util.HashSet;
import java.util.Set;

public class Warehouse {
    private Coordinates coordinates;
    private int id;
    private String name;
    private Set<StockItem> items;

    public Warehouse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Warehouse(int id, String name, Coordinates coordinates) {
        this.setCoordinates(coordinates);
        this.setId(id);
        this.name = name;
        this.items = new HashSet<>();
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

    public Set<StockItem> getItems() {
        return items;
    }

    public void setItems(Set<StockItem> items) {
        this.items = items;
    }
}
