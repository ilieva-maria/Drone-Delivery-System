package com.hackbulgaria.ddsystem.models;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Drone {
    private int id;
    private int batteryUnits;
    private int weightUnits;
    private float chargingRate;
    private Time readyToDeliver;
    public static int MAX_BT = 2_000;

    public Drone() {
    }

    public Time getReadyToDeliver() {
        return readyToDeliver;
    }

    public void setReadyToDeliver(Time readyToDeliver) {
        this.readyToDeliver = readyToDeliver;
    }

    public Drone(int batteryUnits, int weightUnits, int chargingRate, Time readyToDeliver) {
        this.batteryUnits = batteryUnits;
        this.weightUnits = weightUnits;
        this.chargingRate = chargingRate;
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

    public void setChargingRate(float chargingRate) {
        this.chargingRate = chargingRate;
    }


    //ToDo Figure out how these will work with Hibernate
    public void makeDelivery(double distance, long time) {
        batteryUnits -= distance;

        Calendar c = Calendar.getInstance();
        c.setTime(new Date(time));

        c.add(Calendar.MINUTE, (int) distance);
        c.add(Calendar.MINUTE, (int) ((MAX_BT - batteryUnits) * chargingRate));
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
