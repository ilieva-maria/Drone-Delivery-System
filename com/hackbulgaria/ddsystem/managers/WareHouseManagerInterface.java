package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.results.ProductResults;

public interface WareHouseManagerInterface {
	public ProductResults checkProducts(Request request);
	
}
