package com.example.customerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class paymentDetailFragmentActivity extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.payments_detail_fragment,container,false);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);


        recyclerView=view.findViewById(R.id.recylerPaymentInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        Call<List<TransactionDetails>> listCall=retrofitInterface.getPaymentDetails();
        listCall.enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {

                show(response.body());
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {

            }
        });





        return view;
    }

    private void show(List<TransactionDetails> response) {
        recyclerAdapter recyclerAdapter=new recyclerAdapter(response);
        recyclerView.setAdapter(recyclerAdapter);
    }
}
