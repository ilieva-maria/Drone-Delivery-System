package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;

import java.util.*;
import java.util.logging.Logger;

public class RequestManager implements RequestManagerInterface {
    private Logger logger;
    private WarehouseManager warehouseManager;
    private DroneManager droneManager;

    public RequestManager(Logger logger, WarehouseManager warehouseManager, DroneManager droneManager) {
        this.logger = logger;
        this.warehouseManager = warehouseManager;
        this.droneManager = droneManager;
    }

    @Override
    public boolean makeDelivery(Request request) {

        logger.info("Request received: " + request.toString());

        long requestTime = request.getTime();
        ProductResults productResults = warehouseManager.checkProducts(request);

        if (productResults != null) {
            logger.info("Requested products are not available.");
            return false;
        }

        DroneResults droneResults = droneManager
                .checkDrones(productResults, request.getCoordinates(), requestTime);

        if (droneResults == null) {
            logger.info("There are no set of drones that can deliver the products.");
            return false;
        }


        List<Drone> assignedDrones = droneResults.getAvailable();

        int totalItemCount = request.getProducts()
                .entrySet()
                .stream()
                .mapToInt(Map.Entry::getValue)
                .sum();
        int itemsPerDrone = totalItemCount / assignedDrones.size();
        int loadTime = 2 * itemsPerDrone;

        //Update drones
        double distance = Coordinates.distance(productResults.getWareHouse(),
                request.getCoordinates());
        droneManager.makeDelivery(assignedDrones, distance, requestTime, loadTime);

        //Update stocks
        warehouseManager.makeDelivery(productResults);

        logger.info("Drones sent: " + assignedDrones);
        logger.info("Total weight:" + productResults.getTotalWeight());
        logger.info("Products sent: " + productResults.getPurchased());
        logger.info("ETA: " + calculateETA(request.getTime(), distance, loadTime));
        return true;
    }

    

    private String calculateETA(long time, double distance, int loadTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time * 1000));
        System.out.println(calendar.getTime().toString());
        calendar.add(Calendar.MINUTE, (int) distance / 2);
        calendar.add(Calendar.MINUTE, loadTime);
        return calendar.getTime().toString();
    }

	@Override
	public boolean makeSupply(Request request) {
		// TODO Auto-generated method stub
		return false;
	}

}
