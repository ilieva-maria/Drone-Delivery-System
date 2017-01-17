package com.hackbulgaria;

import com.hackbulgaria.managers.WareHouseManager;
import com.hackbulgaria.models.Request;

public interface ClientInterface {
	public void sendRequest(Request request, WareHouseManager warehouse);
	
}
