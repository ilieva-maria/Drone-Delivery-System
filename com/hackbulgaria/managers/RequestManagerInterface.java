package com.hackbulgaria.managers;

import com.hackbulgaria.models.Request;

public interface RequestManagerInterface {
	
	public boolean canMakeDelivery(WareHouseManager warehouseManager, DroneManager droneManager, Request request);
}
