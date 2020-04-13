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

public class registerpageFragmentActivity extends Fragment {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";


     EditText name,password,dob,phone,email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.registerpage_fragment,container,false);

        //retrofit initialization and all the formate controller

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        name=view.findViewById(R.id.regName);
        password=view.findViewById(R.id.regPass);
        dob=view.findViewById(R.id.regdob);
        phone=view.findViewById(R.id.regPhone);
        email=view.findViewById(R.id.regEmail);




        view.findViewById(R.id.register_submit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String,String> map1=new HashMap<>();

                map1.put("name",name.getText().toString());
                map1.put("password",password.getText().toString());
                map1.put("dob",dob.getText().toString());
                map1.put("phone",phone.getText().toString());
                map1.put("email",email.getText().toString());


                Call<previewRegistrationResul> call=retrofitInterface.executeCustomerRegistration(map1);
                call.enqueue(new Callback<previewRegistrationResul>() {
                    @Override
                    public void onResponse(Call<previewRegistrationResul> call, Response<previewRegistrationResul> response) {
                        if(response.code()==204){
                            Toast.makeText(getContext(),"Not inserted ",Toast.LENGTH_SHORT).show();
                        }
                        if(response.code()==202){
                           // shiftActivity();
                            clearBackStack();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new sampleRegistrationQrPreviewFragment()).addToBackStack(null).commit();
                            previewRegistrationResul previewRegistrationResul=response.body();
                            setterRegisterPreview setterRegisterPreview=new setterRegisterPreview();
                            setterRegisterPreview.setCust_user_name(previewRegistrationResul.getCust_user_name());
                            setterRegisterPreview.setBalance(previewRegistrationResul.getBalance());


                            Toast.makeText(getContext(),"inserted "+previewRegistrationResul.getCust_user_name(),Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<previewRegistrationResul> call, Throwable t) {
                        Toast.makeText(getContext(),"have error     "+t,Toast.LENGTH_LONG).show();

                    }
                });


            }
        });


        view.findViewById(R.id.nav_to_login_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new LoginPageActivity()).addToBackStack(null).commit();


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
