package com.example.conductorapp;

import com.google.gson.annotations.SerializedName;

public class setter {

    @SerializedName("bus_name")
    private String bus_name;
    @SerializedName(("bus_number"))
    private static String bus_number;
    @SerializedName("route_end_point_1")
    private String route_end_point_1;
    @SerializedName(("route_end_point_2"))
    private String route_end_point_2;
    @SerializedName("route_via")
    private  String route_via;
    @SerializedName("bus_user_name")
    private String bus_user_name;
    @SerializedName(("bus_password"))
    private String bus_password;


    //this has to be altered
    @SerializedName("cust_id")
    private static String cust_id;

    @SerializedName("route_id")
    private static String route_id;

    @SerializedName("route_cost")
    private static String route_cost;

    public static String getRoute_cost() {
        return route_cost;
    }

    public void setRoute_cost(String route_cost) {
        this.route_cost = route_cost;
    }

    public static String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public static String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public static String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getRoute_end_point_1() {
        return route_end_point_1;
    }

    public void setRoute_end_point_1(String route_end_point_1) {
        this.route_end_point_1 = route_end_point_1;
    }

    public String getRoute_end_point_2() {
        return route_end_point_2;
    }

    public void setRoute_end_point_2(String route_end_point_2) {
        this.route_end_point_2 = route_end_point_2;
    }

    public String getRoute_via() {
        return route_via;
    }

    public void setRoute_via(String route_via) {
        this.route_via = route_via;
    }

    public String getBus_user_name() {
        return bus_user_name;
    }

    public void setBus_user_name(String bus_user_name) {
        this.bus_user_name = bus_user_name;
    }

    public String getBus_password() {
        return bus_password;
    }

    public void setBus_password(String bus_password) {
        this.bus_password = bus_password;
    }
}
