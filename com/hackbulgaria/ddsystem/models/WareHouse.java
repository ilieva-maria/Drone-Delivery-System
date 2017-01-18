package com.hackbulgaria.ddsystem.models;

public class WareHouse {
    private Coordinates coordinates;
	private int id;

    public WareHouse(int id,Coordinates coordinates) {
        this.coordinates = coordinates;
        this.id = id;
    }
}
