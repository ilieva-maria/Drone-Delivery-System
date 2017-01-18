package com.hackbulgaria.ddsystem.results;

import com.hackbulgaria.ddsystem.models.Drone;

import java.util.List;

/**
 * Created by Bilal on 18/01/2017.
 */
public class DroneResults {
    private List<Drone> availableDrones;

    public DroneResults(List<Drone> assignedDrones) {
        this.availableDrones = assignedDrones;
    }

    public List<Drone> getAvailable() {
        return availableDrones;
    }

    public void setAvailableDrones(List<Drone> availableDrones) {
        this.availableDrones = availableDrones;
    }
}
