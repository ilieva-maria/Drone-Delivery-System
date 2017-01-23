package com.hackbulgaria.ddsystem.models;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hackbulgaria.database.Products;

public class Request {
    private int id;
    private Time time;
    private Coordinates coordinates;
    private List<StockItem> products;
    private Map<Products, Integer> productsMap = new HashMap<>();
    
    public Request() {
        // TODO Auto-generated constructor stub
    }

    public Request(int id, Time time, Coordinates coordinates,
                   List<StockItem> products) {
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

    public List<StockItem> getProducts() {
        return products;
    }

    public void setProducts(List<StockItem> products) {
        this.products = products;
    }

	public Map<Products, Integer> getProductsMap() {
		return productsMap;
	}

	public void setProductsMap(Map<Products, Integer> productsMap) {
		this.productsMap = productsMap;
	}
}
