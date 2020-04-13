package com.example.customerapp;

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

public class LoginPageActivity extends Fragment {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login_fragment,container,false);



        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        final EditText username,password;

        username=view.findViewById(R.id.usernameLogin);
        password=view.findViewById(R.id.passwordLogin);

        view.findViewById(R.id.loginpageloginbnt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String,String> map2=new HashMap<>();
                map2.put("username",username.getText().toString());
                map2.put("password",password.getText().toString());

                Call<PreviewLoginResult> call=retrofitInterface.executeCustomerPreviewLoginResult(map2);

                call.enqueue(new Callback<PreviewLoginResult>() {
                    @Override
                    public void onResponse(Call<PreviewLoginResult> call, Response<PreviewLoginResult> response) {
                        if (response.code() == 202) {
                            PreviewLoginResult previewLoginResult = response.body();
                            setterRegisterPreview setterRegisterPreview=new setterRegisterPreview();
                            setterRegisterPreview.setCust_user_name(previewLoginResult.getCust_user_name());
                            setterRegisterPreview.setCust_password(previewLoginResult.getCust_password());
                            setterRegisterPreview.setBalance(previewLoginResult.getBalance());
                            clearBackStack();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new dashboardFragmentActivity()).commit();
                        }
                        else{
                            Toast.makeText(getContext(),"There might be server error or please check your password and user id",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PreviewLoginResult> call, Throwable t) {
                        Toast.makeText(getContext(),"error has occured please contact the admin",Toast.LENGTH_SHORT).show();


                    }
                });



            }
        });

        view.findViewById(R.id.registerLink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new registerpageFragmentActivity()).commit();
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
