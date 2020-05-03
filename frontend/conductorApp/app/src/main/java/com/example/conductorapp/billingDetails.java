package com.example.conductorapp;

import com.google.gson.annotations.SerializedName;

public class billingDetails {
    @SerializedName("route_cost")
    private String route_cost;

    @SerializedName("route_id")
    private String route_id;

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_cost() {
        return route_cost;
    }

    public void setRoute_cost(String route_cost) {
        this.route_cost = route_cost;
    }
}
