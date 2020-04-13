package com.example.customerapp;

import com.google.gson.annotations.SerializedName;

public class setterRegisterPreview {

    @SerializedName("cust_user_name")
    private static String cust_user_name;
    @SerializedName("cust_password")
    private static String cust_password;

    public static String getCust_password() {
        return cust_password;
    }

    public  void setCust_password(String cust_password) {
        this.cust_password = cust_password;
    }

    @SerializedName("balance")
    private static String balance;

    public static String getCust_user_name() {
        return cust_user_name;
    }

    public void setCust_user_name(String cust_user_name) {
        this.cust_user_name = cust_user_name;
    }

    public static String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
