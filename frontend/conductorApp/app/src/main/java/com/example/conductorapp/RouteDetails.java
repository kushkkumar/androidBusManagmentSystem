package com.example.conductorapp;

import com.google.gson.annotations.SerializedName;

public class RouteDetails {


    @SerializedName("brid")
    private String brid;
     @SerializedName("bus_id")
    private String bus_id;
     @SerializedName("route_name")
     public String route_name;

    public String getBrid() {
        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }
}
