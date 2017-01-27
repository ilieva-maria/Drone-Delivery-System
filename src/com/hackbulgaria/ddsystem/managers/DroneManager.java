package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;

import java.util.List;

public interface DroneManager {
    //Check if drones can do a delivery
    DroneResults checkDrones(ProductResults results, Coordinates dest, long time);

    //Update readyToDeliver times for drones that'll make a delivery
    void makeDelivery(List<Drone> assignedDrones, double distance, long time, int loadTime);
}
