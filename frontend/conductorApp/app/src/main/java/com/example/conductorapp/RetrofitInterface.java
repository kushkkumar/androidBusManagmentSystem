package com.example.conductorapp;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/billingDetails")
    Call<billingDetails> getBillingDetail(@Body HashMap<String,String> map);

    @POST("/conductorLogin")
    Call<LoginResultDetails> executeLoginResult(@Body HashMap<String,String> map);

    //edited
    @POST("/checkCustomerPresent")
    Call<customerDetails> customerConfirmationResult(@Body HashMap<String,String> map);


    @POST("transactionDetails")
    Call<List<TransactionDetails>> detailsOfPaymentPage(@Body HashMap<String,String> map);

    @POST("/routeDetails")
    Call<List<RouteDetails>> detailsOfRoute();

    @POST("/insertToTransaction")
    Call<Void> insertToTransaction (@Body HashMap<String ,String> map);








}
