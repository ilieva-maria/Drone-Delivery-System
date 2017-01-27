package com.hackbulgaria.ddsystem.managers;

import com.hackbulgaria.ddsystem.models.Request;

public interface RequestManagerInterface {
    public boolean canMakeDelivery(Request request);
}
