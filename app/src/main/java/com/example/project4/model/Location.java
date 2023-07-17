package com.example.project4.model;

import java.io.Serializable;

public class Location implements Serializable {
    private double longl,lat1;

    public Location() {
    }

    public Location(double longl, double lat1) {
        this.longl = longl;
        this.lat1 = lat1;
    }

    public double getLongt() {
        return longl;
    }

    public void setLongt(double longt) {
        this.longl = longt;
    }

    public double getLat() {
        return lat1;
    }

    public void setLat(double lat) {
        this.lat1 = lat;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longt=" + getLongt() +
                ", lat=" + getLat() +
                '}';
    }
}
