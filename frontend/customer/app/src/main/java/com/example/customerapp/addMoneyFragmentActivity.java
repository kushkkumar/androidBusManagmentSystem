package com.example.customerapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addMoneyFragmentActivity extends Fragment {
    EditText addMoney;

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.add_money_fragment,container,false);



        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);



        final int curbal=Integer.parseInt(setterRegisterPreview.getBalance());
        addMoney=view.findViewById(R.id.enterMoneyId);


        view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float temp=Float.parseFloat(addMoney.getText().toString());
                final int res= (int) (curbal+temp);


               // Toast.makeText(getContext(),""+res,Toast.LENGTH_SHORT).show();
                HashMap<String,String> map3=new HashMap<>();
                map3.put("username",setterRegisterPreview.getCust_user_name());
                map3.put("balupdate",addMoney.getText().toString());


                Call<Void> call=retrofitInterface.executeMoneyUpdate(map3);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==502){
                            setterRegisterPreview s2=new setterRegisterPreview();
                            s2.setBalance(String.valueOf(res));
                            clearBackStack();
                            Toast.makeText(getContext(),"Amount has been added \nYour updated balance is "+res+" Plesase check your account !!",Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new dashboardFragmentActivity()).commit();

                        }
                        else{
                            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(),"error main"+t,Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });



        return view;
    }
    private void clearBackStack() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
