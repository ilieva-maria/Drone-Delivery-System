package com.hackbulgaria.ddsystem.results;

import com.hackbulgaria.ddsystem.models.Drone;

import java.sql.Time;
import java.util.List;

/**
 * Created by Bilal on 18/01/2017.
 */
public class DroneResults {
    private List<Drone> availableDrones;
    private Time arrivalTime;

    public DroneResults(List<Drone> assignedDrones) {
        this.availableDrones = assignedDrones;
    }

    public List<Drone> getAvailable() {
        return availableDrones;
    }

    public void setAvailableDrones(List<Drone> availableDrones) {
        this.availableDrones = availableDrones;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
