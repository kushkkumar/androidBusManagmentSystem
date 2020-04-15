package com.example.conductorapp;

import android.os.Bundle;
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

public class firstLoginFragment extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";

    EditText username,password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_login_page,container,false);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);


        username=view.findViewById(R.id.conductorLoginUname);
        password=view.findViewById(R.id.conductorLoginPassword);

        view.findViewById(R.id.loginToDashbordBnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map =new HashMap<>();

                map.put("username",username.getText().toString());
                map.put("password",password.getText().toString());

                Call<LoginResultDetails> call=retrofitInterface.executeLoginResult(map);

                call.enqueue(new Callback<LoginResultDetails>() {
                    @Override
                    public void onResponse(Call<LoginResultDetails> call, Response<LoginResultDetails> response) {
                        if(response.code()==202){
                            LoginResultDetails loginResultDetails=response.body();
                            setter setter=new setter();
                            setter.setBus_number(loginResultDetails.getBus_number());
                            Toast.makeText(getContext(),"Hello welcome Again!!! to bus "+loginResultDetails.getBus_name(),Toast.LENGTH_SHORT).show();
                            clearBackStack();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frade1,new dashboardFragmentActivity()).commit();

                        }
                        else{
                            Toast.makeText(getContext(),"error happend in the database",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResultDetails> call, Throwable t) {
                        Toast.makeText(getContext(),"wrong credentials contact admin ",Toast.LENGTH_LONG).show();


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
