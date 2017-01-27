package com.hackbulgaria.ddsystem.models;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Drone {
    public static int MAX_BT = 2_000;
    private int id;
    private int batteryUnits;
    private int weightUnits;
    private double chargingRate;
    private Date readyToDeliver;

    public Drone() {
    }

    public Drone(int batteryUnits, int weightUnits, int chargingRate, Date readyToDeliver) {
        this.batteryUnits = batteryUnits;
        this.weightUnits = weightUnits;
        this.chargingRate = chargingRate;
        this.readyToDeliver = readyToDeliver;
    }

    public Date getReadyToDeliver() {
        return readyToDeliver;
    }

    public void setReadyToDeliver(Date readyToDeliver) {
        this.readyToDeliver = readyToDeliver;
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


    public void makeDelivery(double distance, long time, int loadTime) {
        batteryUnits -= (int) distance;

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(time * 1000));

        c.add(Calendar.MINUTE, loadTime);
        c.add(Calendar.MINUTE, (int) distance);
        c.add(Calendar.MINUTE, (int) ((MAX_BT - batteryUnits) / chargingRate));
        readyToDeliver = new Time(c.getTime().getTime());
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", batteryUnits=" + batteryUnits +
                ", weightUnits=" + weightUnits +
                ", chargingRate=" + chargingRate +
                ", readyToDeliver=" + readyToDeliver +
                '}';
    }
}
