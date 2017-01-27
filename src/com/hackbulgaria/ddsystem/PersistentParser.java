package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Product;
import com.hackbulgaria.ddsystem.models.Request;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses input with the items fetched from
 */
public class PersistentParser implements RequestParser {

    private Session session;


    public PersistentParser(Session session) {
        this.session = session;
    }


    @Override
    public Request parse(String userRequest) {
        Request request = new Request();

        String[] query = userRequest.split("\\s+");
        request.setId(Integer.parseInt(query[1]));
        request.setTime(Long.parseLong(query[2]));

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

        Map<Product, Integer> products = new HashMap<>();

        for (int i = 4; i < query.length; i += 2) {
            int productID = Integer.parseInt(query[i]);

            Product product = session.get(Product.class, productID);
            if (product != null) {
                int quantity = Integer.parseInt(query[i + 1]);
                products.put(product, quantity);
            }
        }
        request.setProducts(products);
        return request;
    }
}
