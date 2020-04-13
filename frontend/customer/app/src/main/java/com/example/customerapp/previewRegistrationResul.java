package com.example.customerapp;

import com.google.gson.annotations.SerializedName;

public class previewRegistrationResul {
    @SerializedName("cust_user_name")
    private String cust_user_name;
    @SerializedName("balance")
    private String balance;

    public String getCust_user_name() {
        return cust_user_name;
    }

    public void setCust_user_name(String cust_user_name) {
        this.cust_user_name = cust_user_name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


}
