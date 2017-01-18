package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Request;

public interface RequestManagerInterface {

    public boolean canMakeDelivery(WareHouseManager warehouseManager,
                                   DroneManager droneManager, Request request);
}
