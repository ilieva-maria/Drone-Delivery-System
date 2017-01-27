package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Product;
import com.hackbulgaria.ddsystem.models.Request;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Parses input with the items fetched from
 */
public class PersistentParser implements RequestParser {

    private Session session;
    private Logger logger;

    public PersistentParser(Session session, Logger logger) {
        this.session = session;
        this.logger = logger;
    }


    @Override
    public Request parse(String userRequest) {
        Request request = new Request();
        String[] query = userRequest.split("\\s+");
        if (query.length < 5) {
        	logger.info("Unable to parse the request. Not enough arguments.");
        	throw new IllegalArgumentException();
        }
        
        request.setRequestType(query[0]);
        request.setId(Integer.parseInt(query[1]));
        request.setTime(Long.parseLong(query[2]));
        
        boolean isDelivery = request.getRequestType()
        		.equals(RequestHandler.DELIVERY);
        
        if (isDelivery) {
			parseTargetCoordinates(request, query);
        }

        parseProducts(request, query, isDelivery);
        return request;
    }


	private void parseProducts(Request request, String[] query, boolean isDelivery) {
		Map<Product, Integer> products = new HashMap<>();
        int s = isDelivery ? 4 : 3;
        for (int i = s; i < query.length; i += 2) {
            int productID = Integer.parseInt(query[i]);

            Product product = session.get(Product.class, productID);
            int quantity = Integer.parseInt(query[i + 1]);
            if (product != null) {
                products.put(product, quantity);
            } else if (product == null && !isDelivery){
               	products.put(new Product("Unknown",1d), quantity);
            }  else {
            	logger.info("The requested product is not in stock. The product ID is : " + productID);
            }
        }
        request.setProducts(products);
	}


	private void parseTargetCoordinates(Request request, String[] query) {
		String[] location = query[3].split(",");
		double lat = Double.parseDouble(location[0].trim());
		double lng = Double.parseDouble(location[1].trim());
		//Check coordinates
		if (lat > 1000 || lng > 1000) {
			throw new IllegalArgumentException("Coordinates are outside the city ");
			
		} else if (lat < 0 || lng < 0) {
			throw new IllegalArgumentException("Coordinates < 0 ");
			
		} else {
			request.setCoordinates(new Coordinates(lat, lng));
		}
	}
}
