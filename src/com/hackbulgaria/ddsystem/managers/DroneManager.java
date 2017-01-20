package com.hackbulgaria.ddsystem.managers;


import com.hackbulgaria.ddsystem.models.Coordinates;
import com.hackbulgaria.ddsystem.models.Drone;
import com.hackbulgaria.ddsystem.results.DroneResults;
import com.hackbulgaria.ddsystem.results.ProductResults;

import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;

public class DroneManager implements DroneManagerInterface {
	
	private Session session;
	private List<Drone> drones;
	
	public DroneManager(Session session) {
		this.session = session;
	}

	public void showDrones() {
		List<?> drones = session.createQuery("FROM Drones").getResultList();
		for (Iterator<?> iterator = drones.iterator(); iterator.hasNext();) {
			Drone drone = (Drone) iterator.next();
			System.out.print("ID: " + drone.getId());
			System.out.print("  BU: " + drone.getBatteryUnits());
			System.out.print("  WU: " + drone.getWeightUnits());
			System.out.println("  ChargingRate: " + drone.getChargingRate());
		}
	}

    

    public DroneManager(List<Drone> drones) {
        this.drones = drones;
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

    public void makeDelivery(List<Drone> drones, double distance, Time time){
        if (drones == null)
            return;

        //ToDo Test this
        drones.forEach(drone -> drone.makeDelivery(distance, time));
    }
}
