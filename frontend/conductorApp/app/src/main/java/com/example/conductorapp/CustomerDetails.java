package com.example.conductorapp;

import com.google.gson.annotations.SerializedName;

public class CustomerDetails {
    @SerializedName("cust_id")
    private String cust_id;

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
}
