package com.hackbulgaria.database;

public class Coordinates {
	private int id;
	private double lat;
	private double lng;
	private Warehouse warehouse;
	
	public Coordinates() {
		// TODO Auto-generated constructor stub
	}
	
	public Coordinates(int lat, int lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getlat() {
		return lat;
	}
	public void setlat(int lat) {
		this.lat = lat;
	}
	public double getlng() {
		return lng;
	}
	public void setlng(int lng) {
		this.lng = lng;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Warehouse getWarehouse() {
		return warehouse;
	}



	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
}
