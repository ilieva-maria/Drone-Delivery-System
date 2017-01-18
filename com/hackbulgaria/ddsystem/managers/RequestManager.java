package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;

import java.util.ArrayList;
import java.util.List;

public class RequestManager implements RequestManagerInterface {

    @Override
    public boolean canMakeDelivery(WareHouseManager warehouseManager,
                                   DroneManager droneManager, Request request) {
        droneManager.updateDrones(request.getTime());

        ProductResults productResults = warehouseManager.checkProducts(request);
        DroneResults droneResults = droneManager.checkDrones(productResults,request.getCoordinates());

        if (droneResults == null) {
            return false;
        }

        List<Drone> assignedDrones = new ArrayList<>();
        int assignedWeight = 0;

        //Assign only drones that'll do the job
        for (int i=0; assignedWeight < productResults.getTotalWeight(); i++){
            Drone assigned = droneResults.getAvailable().get(i);
            assignedDrones.add(assigned);

            assignedWeight += assigned.getCapacity();
        }

        //Update batteries
        double distance = Coordinates.distance(productResults.getWareHouse(),
                request.getCoordinates());
        droneManager.makeDelivery(assignedDrones, distance, request.getTime());

        return true;
    }

}
