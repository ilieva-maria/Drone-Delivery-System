package com.hackbulgaria.ddsystem.results;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.StockItem;

import java.util.ArrayList;
import java.util.List;

public class ProductResults {
    private boolean available;
    private float totalWeight;
    private Coordinates wareHouse;
    private List<StockItem> purchased;

    public ProductResults(boolean available, float totalWeight, Coordinates wareHouse) {
        this.available = available;
        this.totalWeight = totalWeight;
        this.wareHouse = wareHouse;
        this.purchased = new ArrayList<>();
    }

    public ProductResults() {
        available = true;
        totalWeight = 0;
        this.purchased = new ArrayList<>();
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

    public void setWareHouse(Coordinates wareHouse) {
        this.wareHouse = wareHouse;
    }

    public void addWeight(double v) {
        this.totalWeight += v;
    }

    public void addPurchasedItem(StockItem item){
        purchased.add(item);
    }

    public List<StockItem> getPurchased() {
        return purchased;
    }
}
