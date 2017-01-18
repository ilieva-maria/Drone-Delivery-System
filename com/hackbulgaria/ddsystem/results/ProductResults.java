package com.hackbulgaria.ddsystem.results;

import com.hackbulgaria.ddsystem.models.Coordinates;

public class ProductResults {
    private boolean available;
    private float totalWeight;
    private Coordinates wareHouse;

    public ProductResults(boolean available, float totalWeight, Coordinates wareHouse) {
        this.available = available;
        this.totalWeight = totalWeight;
        this.wareHouse = wareHouse;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Coordinates getWareHouse() {
        return wareHouse;
    }
}
