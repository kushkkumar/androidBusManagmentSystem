package com.example.conductorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE =101;


     private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL="https://pure-sea-79661.herokuapp.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface=retrofit.create(RetrofitInterface.class);

        findViewById(R.id.qrScanner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission(Manifest.permission.CAMERA)){
                        openScanner();
                    }
                    else
                        requestPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE);
                }
                else{
                    openScanner();
                }

            }
        });




        findViewById(R.id.checkForTheCustomer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map1=new HashMap<>();

                map1.put("username", ((EditText)findViewById(R.id.custUserNameIssuid)).getText().toString());

                Call<customerDetails> call=retrofitInterface.customerConfirmationResult(map1);
                call.enqueue(new Callback<customerDetails>() {
                    @Override
                    public void onResponse(Call<customerDetails> call, Response<customerDetails> response) {
                        if(response.code()==602){
                            Toast.makeText(getApplicationContext(),"He is a customer and have enough balance",Toast.LENGTH_LONG).show();
                            customerDetails customerDetails=response.body();
                        }
                        else if(response.code()==604){
                            Toast.makeText(getApplicationContext(),"He is a customer but do not have enough balance",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"He is not a customer",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<customerDetails> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"This person is not the user",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });











    }
    private void openScanner() {
        new IntentIntegrator(Main2Activity.this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"blank",Toast.LENGTH_SHORT).show();
            }
            else{
                ((EditText)findViewById(R.id.custUserNameIssuid)).setText(result.getContents());
            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Blank",Toast.LENGTH_SHORT).show();
        }
    }




    //to check whether it works on below API level if not provide the permission




    private boolean checkPermission(String permission){
        int result= ContextCompat.checkSelfPermission(Main2Activity.this,permission);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else
            return false;
    }
    private void requestPermission(String permission,int code){

        if(ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this,permission)){

        }
        else
        {
            ActivityCompat.requestPermissions(Main2Activity.this,new String[]{permission},code);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CAMERA_PERMISSION_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openScanner();
                }

        }
    }
}
