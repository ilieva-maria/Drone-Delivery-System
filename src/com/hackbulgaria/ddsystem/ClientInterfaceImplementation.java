package com.hackbulgaria.ddsystem;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.hackbulgaria.database.Products;
import com.hackbulgaria.ddsystem.managers.RequestManager;
import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.models.StockItem;

public class ClientInterfaceImplementation implements ClientInterface {
    private Session session;


    public ClientInterfaceImplementation(Session session) {
        this.session = session;
    }

    public ClientInterfaceImplementation() {
    }

    @Override
    public void makeRequest(Request request, RequestManager requestManager) {
        // TODO Auto-generated method stub

    }

    @Override
    public Request parseInput(String userRequest) {
        String[] query = userRequest.split("\\s+");
        Request request = new Request();
        request.setId(Integer.parseInt(query[1]));

        String timestamp = query[2];
        if (timestamp.equals("now")){
            request.setTime(new Time(System.currentTimeMillis()));
        } else {
            long t = Long.valueOf(timestamp);
            request.setTime(new Time(t));
        }

        String[] location = query[3].split(",");
        double lat = Double.parseDouble(location[0].trim());
        double lng = Double.parseDouble(location[1].trim());

        //check coordinates
        if (lat > 1000 || lng > 1000) {
            throw new IllegalArgumentException("Coordinates are outside the city ");

        } else if (lat < 0 || lng < 0) {
            throw new IllegalArgumentException("Coordinates < 0 ");

        } else {
            request.setCoordinates(new Coordinates(lat, lng));
        }

        List<StockItem> items = new ArrayList<>();
        for(int i=4; i<query.length;i++){
            StockItem stockItem = new StockItem(Integer.valueOf(query[i]), Integer.valueOf(query[++i]), null);
            items.add(stockItem);
        }
        request.setProducts(items);
        return request;
    }
    
    @Override
    public Request parseInputWithoutTime(String userRequest) {
		String[] query = userRequest.split("\\s+");
		Request request = new Request();
		request.setId(Integer.parseInt(query[1]));
		
		String[] location = query[4].split(",");
		double lat = Double.parseDouble(location[0].trim());
		double lng = Double.parseDouble(location[1].trim());
		
	
		//check coordinates
		if(lat > 1000 || lng > 1000){
			throw new IllegalArgumentException("Coodrdinates are outside the city ");
			
		}else if(lat < 0 || lng < 0){
			throw new IllegalArgumentException("Coodrdinates < 0 ");
			
		}else{
			request.setCoordinates(new Coordinates(lat, lng));
		}
		
		Map<Products, Integer> products = new HashMap<>();
		
		
		for (int i = 5; i < query.length; i+=2) {
			int productID = Integer.parseInt(query[i]); 
			Products product = (Products) session.get(Products.class, productID);
			int quantity=Integer.parseInt(query[i+1]);			
			products.put(product, quantity);
		}
		request.setProductsMap(products);
		return request;
	}
}
