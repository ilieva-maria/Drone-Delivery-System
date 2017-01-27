package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Request;

public interface RequestManagerInterface {
    public boolean makeDelivery(Request request);
    public boolean makeSupply(Request request);
}
