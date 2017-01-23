package com.hackbulgaria.database;

public class Drones {
	private int id;
	private int batteryUnits;
	private int weightUnits;
	private double chargingRate;
	
	public Drones() {}
	
	public Drones(int batteryUnits, int weightUnits, int chargingRate) {
		this.batteryUnits = batteryUnits;
		this.weightUnits = weightUnits;
		this.chargingRate = chargingRate;
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
}
