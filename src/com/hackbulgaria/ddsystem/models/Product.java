package com.hackbulgaria.ddsystem.models;

public class Product {
    private int id;
    private String name;
    private double weight;
    private StockItem item;

    public Product() {
    }

    public Product(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name + ", weight:" + weight + " ";
    }

    public StockItem getItem() {
        return item;
    }

    public void setItem(StockItem item) {
        this.item = item;
    }
}
