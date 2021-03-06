package com.example.conductorapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class paymentDetailsFragmentActivity extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";

    RecyclerView recyclerVie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.payment_detail_fragment,container,false);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        recyclerVie=view.findViewById(R.id.recyclerview);
        recyclerVie.setHasFixedSize(true);
        recyclerVie.setLayoutManager(new LinearLayoutManager(getContext()));



        HashMap<String,String> map2=new HashMap<>();
        map2.put("busid",setter.getBus_number());


        Call<List<TransactionDetails>> listCall=retrofitInterface.detailsOfPaymentPage(map2);
        listCall.enqueue(new Callback<List<TransactionDetails>>() {
            @Override
            public void onResponse(Call<List<TransactionDetails>> call, Response<List<TransactionDetails>> response) {
                show(response.body());
            }

            @Override
            public void onFailure(Call<List<TransactionDetails>> call, Throwable t) {
                Toast.makeText(getContext(),"Errr"+t,Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }


    private void show(List<TransactionDetails> response) {

        recyclerAdapter recyclerAdapter=new recyclerAdapter(response);
        recyclerVie.setAdapter(recyclerAdapter);




    }
}
