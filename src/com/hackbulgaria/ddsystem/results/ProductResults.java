package com.hackbulgaria.ddsystem.results;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.StockItem;

import java.util.ArrayList;
import java.util.List;

public class ProductResults {
    private float totalWeight;
    private Coordinates wareHouse;
    private List<StockItem> purchased;

    public ProductResults(float totalWeight, Coordinates wareHouse) {
        this.totalWeight = totalWeight;
        this.wareHouse = wareHouse;
        this.purchased = new ArrayList<>();
    }

    public ProductResults() {
        totalWeight = 0;
        this.purchased = new ArrayList<>();
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
