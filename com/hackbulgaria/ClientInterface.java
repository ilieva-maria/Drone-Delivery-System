package com.hackbulgaria;

import com.hackbulgaria.managers.RequestManager;
import com.hackbulgaria.models.Request;

public interface ClientInterface {
	public void makeRequest(Request request, RequestManager requestManager);
	public Request parseInput(String userRequest);
	
}
