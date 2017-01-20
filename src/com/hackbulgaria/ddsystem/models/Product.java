package com.hackbulgaria.ddsystem.models;

public class Product {
    private int id;
    private String name;
    private float weight;

    public Product() {
    }

    public Product(int id, String name, float weight) {
        this.id = id;
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

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name + ", weight:" + weight + " ";
    }
}
