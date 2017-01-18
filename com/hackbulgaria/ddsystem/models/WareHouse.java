package com.hackbulgaria.ddsystem.models;

public class WareHouse {
    private Coordinates coordinates;
	private int id;

    public WareHouse(int id,Coordinates coordinates) {
        this.setCoordinates(coordinates);
        this.setId(id);
    }

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
