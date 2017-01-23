package com.hackbulgaria.ddsystem.managers;


import com.hackbulgaria.database.Drones;
import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;
import org.hibernate.Session;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class DroneManager implements DroneManagerInterface {
    private static String TABLE_NAME = "Drones";
    private List<Drone> drones;
    private Session session;
    
    public DroneManager() {}
    
    public DroneManager(Session session) {
		this.session = session;
	}

	public void showDronesFromTable() {
		List<?> drones = session.createQuery("FROM Drones").getResultList();
		for (Iterator<?> iterator = drones.iterator(); iterator.hasNext();) {
			Drones drone = (Drones) iterator.next();
			System.out.print("ID: " + drone.getId());
			System.out.print("  BU: " + drone.getBatteryUnits());
			System.out.print("  WU: " + drone.getWeightUnits());
			System.out.println("  ChargingRate: " + drone.getChargingRate());
		}
	}

    // Factory style constructor
    public static DroneManager fromList(List<Drone> drones) {
        DroneManager droneManager = new DroneManager();
        droneManager.drones = drones;
        return droneManager;
    }

    @SuppressWarnings("unchecked")
    public static DroneManager fromDatabase(Session session) {
        DroneManager droneManager = new DroneManager();
        droneManager.drones = session
                .createQuery("FROM " + TABLE_NAME)
                .getResultList();
        return droneManager;
    }

    public void showDrones() {
        drones.forEach(System.out::println);
    }


    @Override
    public DroneResults checkDrones(ProductResults pResults, Coordinates dest) {
        if (!pResults.isAvailable()) {
            //The product is not available - no point in assigning drones
            return null;
        }

        //Calculate the distance of the delivery
        double distance = Coordinates.distance(dest, pResults.getWareHouse());

        //Filter the available drones
        List<Drone> available =
                drones.stream()
                        .filter(drone -> drone.canTravel(distance))
                        .collect(Collectors.toList());

        int totalCarry = available.stream().mapToInt(Drone::getWeightUnits).sum();
        if (totalCarry < pResults.getTotalWeight()) {
            //Our drones cannot carry the weight
            return null;
        }

        return new DroneResults(available);
    }

    @Override
    public void updateDrones(Time time) {
        //ToDo Test this
        drones.forEach(drone -> drone.updateBattery(time));
    }

    public void makeDelivery(List<Drone> drones, double distance, Time time) {
        if (drones == null)
            return;

        //ToDo Test this
        drones.forEach(drone -> drone.makeDelivery(distance, time));
    }
}
