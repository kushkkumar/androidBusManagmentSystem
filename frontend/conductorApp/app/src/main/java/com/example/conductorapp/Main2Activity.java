package com.example.conductorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_CODE =101;
    Spinner routeFrom,routeTo;
    String routeNameFrom,routeNameTo;
    EditText cohe;
    String coh;



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

//                            ****************************************************************
                            customerDetails customerDetails=response.body();
                            setter setter=new setter();
                            setter.setCust_id(customerDetails.getCust_id());
                           System.out.println(""+customerDetails.getCust_id());

//                           *****************************************************************


                            Toast.makeText(getApplicationContext(),"He is a customer and have enough balance",Toast.LENGTH_LONG).show();

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

         routeFrom =findViewById(R.id.routeFrom);
         routeTo =findViewById(R.id.routeDetailsTo);

         Call<List<RouteDetails>> listCall1=retrofitInterface.detailsOfRoute();
         listCall1.enqueue(new Callback<List<RouteDetails>>() {
             @Override
             public void onResponse(Call<List<RouteDetails>> call, Response<List<RouteDetails>> response) {

                 ArrayList<String> strings=new ArrayList<>();
                 for(RouteDetails x:response.body()){
                     strings.add(x.getRoute_name());
                 }
                 final String[] arr=new String[strings.size()];

                 int i=0;

                 for(String s : strings){
                     arr[i++]=s;
                 }

                 ArrayAdapter ap=new ArrayAdapter<String>(getApplicationContext(),R.layout.color_spinner,arr);
                 ap.setDropDownViewResource(R.layout.spinner_dropdown);
                 routeFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         routeNameFrom=arr[position];
                        // Toast.makeText(getApplicationContext(),""+routeNameFrom,Toast.LENGTH_SHORT).show();

                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }
                 });
                 routeFrom.setAdapter(ap);


                 ArrayAdapter ap1=new ArrayAdapter<String>(getApplicationContext(),R.layout.color_spinner,arr);
                 ap1.setDropDownViewResource(R.layout.spinner_dropdown);

                 routeTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                     @Override
                     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                         routeNameTo=arr[position];
                     }

                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {

                     }
                 });
                 routeTo.setAdapter(ap1);


             }

             @Override
             public void onFailure(Call<List<RouteDetails>> call, Throwable t) {

             }
         });





         findViewById(R.id.billingDetaild).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 cohe=findViewById(R.id.cohEntry);
                 coh=cohe.getText().toString();




                 HashMap<String,String> map4=new HashMap<>();
                 map4.put("routeFrom",routeNameFrom);
                 map4.put("routeTo",routeNameTo);

                 Call<billingDetails> call7=retrofitInterface.getBillingDetail(map4);
                 call7.enqueue(new Callback<billingDetails>() {
                     @Override
                     public void onResponse(Call<billingDetails> call, Response<billingDetails> response) {
                         if (response.code()==300){
                             Toast.makeText(getApplicationContext(),"error in db",Toast.LENGTH_SHORT).show();
                         }
                         else if(response.code()==302){
                             billingDetails bd=response.body();

                             Toast.makeText(getApplicationContext(),"proper "+bd.getRoute_cost(),Toast.LENGTH_SHORT).show();

//                              ************************************************************************
                             billingDetails billingDetails=response.body();
                             setter setter=new setter();
                             setter.setRoute_id(billingDetails.getRoute_id());
                             setter.setRoute_cost(billingDetails.getRoute_cost());

//                             *******************************************************************

                             confirmTicket();
                         }
                         else{
                             Toast.makeText(getApplicationContext(),"error ",Toast.LENGTH_SHORT).show();
                         }

                     }

                     @Override
                     public void onFailure(Call<billingDetails> call, Throwable t) {
                         Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();

                     }
                 });
             }
         });















    }

    private void confirmTicket() {
        AlertDialog.Builder builder;
        builder=new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("Confirm")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                        String formattedDate = df.format(c);

                        HashMap<String,String> map7=new HashMap<>();
                        map7.put("busid",setter.getBus_number());
                        map7.put("custid",setter.getCust_id());
                        map7.put("routeid",setter.getRoute_id());
                        map7.put("coh",coh);
                        map7.put("cph",setter.getRoute_cost());
                        //map7.put("total",(Integer.parseInt(setter.getRoute_cost())*Integer.parseInt(coh)));
                        map7.put("transDate",formattedDate);

                        Call<Void> call=retrofitInterface.insertToTransaction(map7);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if(response.code()==406){
                                    Toast.makeText(getApplicationContext(),"Ticket is issued",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"error in db",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();



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
