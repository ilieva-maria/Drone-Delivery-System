package com.hackbulgaria.ddsystem.models;

import com.hackbulgaria.ddsystem.models.Warehouse;

public class Coordinates {
    private int id;
    private double lat;
    private double lng;
    private Warehouse warehouse;

    public Coordinates() {
        // TODO Auto-generated constructor stub
    }

    public Coordinates(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double getlat() {
        return lat;
    }

    public void setlat(int lat) {
        this.lat = lat;
    }

    public double getlng() {
        return lng;
    }

    public void setlng(int lng) {
        this.lng = lng;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Warehouse getWarehouse() {
        return warehouse;
    }


    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public static double distance(Coordinates a, Coordinates b) {
        return 2 * Math.sqrt((a.lat - b.lng) * (a.lat - b.lng) +
                (a.lat - b.lng) * (a.lat - b.lng));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        return (lat == that.lat) && (lng == that.lng);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
