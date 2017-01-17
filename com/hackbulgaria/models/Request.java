package com.hackbulgaria.models;

import java.sql.Time;
import java.util.HashMap;

public class Request {
	 int id;
	 Time time;
	 Coordinates coordinates;
	 HashMap<Product, Integer> products;
}
