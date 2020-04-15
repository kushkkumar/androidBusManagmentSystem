package com.example.conductorapp;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/conductorLogin")
    Call<LoginResultDetails> executeLoginResult(@Body HashMap<String,String> map);

    @POST("/checkCustomerPresent")
    Call<customerDetails> customerConfirmationResult(@Body HashMap<String,String> map);


    @POST("transactionDetails")
    Call<List<TransactionDetails>> detailsOfPaymentPage(@Body HashMap<String,String> map);
}
