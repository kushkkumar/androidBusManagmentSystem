package com.example.customerapp;

import com.google.gson.annotations.SerializedName;

public class TransactionDetails {

    @SerializedName("transaction_id")
    private String transaction_id;
    @SerializedName("bus_id")
    private String bus_id;
    @SerializedName("route_id")
    private String route_id;
    @SerializedName("count_of_head")
    private String count_of_head;
    @SerializedName("cost_per_head")
    private String cost_per_head;
    @SerializedName("total_cost")
    private String total_cost;
    @SerializedName("transaction_date")
    private String transaction_date;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getCount_of_head() {
        return count_of_head;
    }

    public void setCount_of_head(String count_of_head) {
        this.count_of_head = count_of_head;
    }

    public String getCost_per_head() {
        return cost_per_head;
    }

    public void setCost_per_head(String cost_per_head) {
        this.cost_per_head = cost_per_head;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }
}
