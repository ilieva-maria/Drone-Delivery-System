package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Request;
import com.hackbulgaria.ddsystem.models.Warehouse;
import com.hackbulgaria.ddsystem.results.ProductResults;

public interface WarehouseManager {
    public ProductResults checkProducts(Request request);

    public void showProducts();

    Warehouse getWarehouse();

    //ToDo Update products when a delivery is made
    public void makeDelivery(ProductResults productResults);
    public void makeSupply(Request request);
}
