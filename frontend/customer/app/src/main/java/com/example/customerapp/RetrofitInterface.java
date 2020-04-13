package com.example.customerapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/loginRegistration")
    Call<PreviewLoginResult> executeCustomerPreviewLoginResult(@Body HashMap<String,String> map);

    @POST("/customerRegistration")
    Call<previewRegistrationResul> executeCustomerRegistration(@Body HashMap<String,String> map);

    @POST("'addMoney")
    Call<Void> executeMoneyUpdate(@Body HashMap<String,String> map);



}
