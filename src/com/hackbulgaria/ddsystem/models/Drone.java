package com.hackbulgaria.ddsystem.models;

import java.sql.Time;

public class Drone {
    private int id;
    private int batteryUnits;
    private int weightUnits;
    private double chargingRate;
    private Time lastUpdated;
    private int MAX_BT = 2_000;

    public Drone() {
    }

    public Time getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Time lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Drone(int batteryUnits, int weightUnits, int chargingRate, Time lastUpdated) {
        this.batteryUnits = batteryUnits;
        this.weightUnits = weightUnits;
        this.chargingRate = chargingRate;
        this.lastUpdated = lastUpdated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBatteryUnits() {
        return batteryUnits;
    }

    public void setBatteryUnits(int batteryUnits) {
        this.batteryUnits = batteryUnits;
    }

    public int getWeightUnits() {
        return weightUnits;
    }

    public void setWeightUnits(int weightUnits) {
        this.weightUnits = weightUnits;
    }

    public double getChargingRate() {
        return chargingRate;
    }

    public void setChargingRate(double chargingRate) {
        this.chargingRate = chargingRate;
    }

    public boolean canTravel(double distance) {
        //Assume our drones use 1
        //Battery Unit per 1 Distance unit
        return batteryUnits > distance;
    }

    //ToDo Figure out how these will work with Hibernate
    public void makeDelivery(double distance, Time time) {
        batteryUnits -= distance;
        lastUpdated = time;
    }

    public void updateBattery(Time time) {
        if (lastUpdated == null) {
            batteryUnits = MAX_BT;
            return;
        }
        if (!lastUpdated.before(time)) {
            long diff = lastUpdated.getTime() - time.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;

            batteryUnits += chargingRate * diffMinutes;
        }
    }

    @Override
    public String toString() {
        return "Drone{" +
                "batteryUnits=" + batteryUnits +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
