package com.hackbulgaria.ddsystem.models;

import java.sql.Time;

public class Drone {
    private int id;
    private int battery;
    private int capacity;
    final private int rechargeRate;
    private int warehouseID;
    private Time lastUpdated;

    public Drone(int id, int battery, int capacity,
                 int rechargeRate, int warehouseID) {
        this.id = id;
        this.battery = battery;
        this.capacity = capacity;
        this.rechargeRate = rechargeRate;
        this.warehouseID = warehouseID;
    }

    public int getId() {
        return id;
    }

    public int getBattery() {
        return battery;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRechargeRate() {
        return rechargeRate;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public boolean canTravel(double distance) {
        //Assume our drones use 1
        //Battery Unit per 1 Distance unit
        return battery > distance;
    }

    public void makeDelivery(double distance, Time time) {
        battery -= distance;
        lastUpdated = time;
    }

    public void updateBattery(Time time) {
        if (!lastUpdated.before(time)) {
            long diff = lastUpdated.getTime() - time.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;

            battery += rechargeRate * diffMinutes;
        }
    }
}
