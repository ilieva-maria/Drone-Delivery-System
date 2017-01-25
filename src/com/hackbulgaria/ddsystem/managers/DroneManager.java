package com.hackbulgaria.ddsystem.managers;


import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;
import org.hibernate.Session;

import java.util.List;

public class DroneManager implements DroneManagerInterface {
    private static String TABLE_NAME = "Drones";
    private Session session;

    public DroneManager() {
    }

    public DroneManager(Session session) {
        this.session = session;
    }

    public void showDronesFromTable() {
        List<?> drones = session.createQuery("FROM Drone").getResultList();
        drones.forEach(System.out::println);
    }

    //Checks if there are enough drones to deliver the products
    @SuppressWarnings("unchecked")
    @Override
    public DroneResults checkDrones(ProductResults pResults, Coordinates dest, long time) {
        if (!pResults.isAvailable()) {
            //The product is not available - no point in assigning drones
            return null;
        }

        //Calculate the distance of the delivery
        double distance = Coordinates.distance(dest, pResults.getWareHouse());

        String query = String.format("FROM Drone WHERE " +
                "readyToDeliver < FROM_UNIXTIME(%d) or readyToDeliver IS NULL", time);

        //Filter the available drones
        List<Drone> available =
                session.createQuery(query).getResultList();

        int totalCarry = available.stream().mapToInt(Drone::getWeightUnits).sum();
        if (totalCarry < pResults.getTotalWeight()) {
            //Our drones cannot carry the weight
            return null;
        }

        return new DroneResults(available);
    }


    //Calculates for all assigned drones when they'll be ready and updates in the DB
    public void makeDelivery(List<Drone> drones, double distance, long time) {
        if (drones == null)
            return;

        drones.forEach(drone -> {
            drone.makeDelivery(distance, time);
            session.update(drone);
        });
    }
}
