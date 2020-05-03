package com.example.conductorapp;

import com.google.gson.annotations.SerializedName;

public class customerDetails {
    @SerializedName("cust_id")
    private String cust_id;
    @SerializedName("cust_user_name")
    private String cust_user_name;

    public String getCust_user_name() {
        return cust_user_name;
    }

    public void setCust_user_name(String cust_user_name) {
        this.cust_user_name = cust_user_name;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
}
