package com.hackbulgaria.ddsystem;

import com.hackbulgaria.ddsystem.models.Request;

public interface RequestParser {
    public Request parse(String string);
}
