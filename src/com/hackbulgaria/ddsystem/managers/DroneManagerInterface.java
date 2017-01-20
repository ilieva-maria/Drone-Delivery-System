package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;

import java.sql.Time;
import java.util.List;

public interface DroneManagerInterface {
    //Check if drones can do a delivery
    DroneResults checkDrones(ProductResults results, Coordinates dest);

    //Update batteries for a given time
    void updateDrones(Time time);

    //Update batteries for drones that'll make a delivery
    void makeDelivery(List<Drone> assignedDrones, double distance, Time time);
}
