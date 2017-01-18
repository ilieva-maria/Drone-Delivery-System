package com.hackbulgaria.database;

import java.util.Set;

import com.hackbulgaria.ddsystem.models.Coordinates;

public class Warehouse {
	private int id;
	private String name;
	private Coordinates coordinates;
	private Set<StockItem> items;
	
	public Warehouse() {}
	
	public Warehouse(Coordinates coordinates, Set<StockItem> items) {
		super();
		this.coordinates = coordinates;
		this.items = items;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public Set<StockItem> getItems() {
		return items;
	}
	public void setItems(Set<StockItem> items) {
		this.items = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
