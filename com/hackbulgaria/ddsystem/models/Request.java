package com.hackbulgaria.ddsystem.models;

import java.sql.Time;
import java.util.HashMap;

public class Request {
	private int id;
	private Time time;
	private Coordinates coordinates;
	private HashMap<Product, Integer> products;

	public Request(int id, Time time, Coordinates coordinates,
				   HashMap<Product, Integer> products) {
		this.id = id;
		this.time = time;
		this.coordinates = coordinates;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public HashMap<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(HashMap<Product, Integer> products) {
		this.products = products;
	}
}
