package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.managers.RequestManager;
import com.hackbulgaria.ddsystem.models.Request;

public interface ClientInterface {
	public void makeRequest(Request request, RequestManager requestManager);
	public Request parseInput(String userRequest);
	
}
