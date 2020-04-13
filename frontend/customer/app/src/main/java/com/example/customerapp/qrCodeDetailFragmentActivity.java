package com.example.customerapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrCodeDetailFragmentActivity extends Fragment {
    TextView username,balance;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.qr_code_fragment,container,false);

        ImageView qrimage=view.findViewById(R.id.qrcode_image);

        String data=setterRegisterPreview.getCust_user_name();
        String bal=setterRegisterPreview.getBalance();

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

        username=view.findViewById(R.id.custUerid);
        balance=view.findViewById(R.id.custBalaId);

        username.setText(data);

        balance.setText(bal);



        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200); //encode the string
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);  //conver into the qr code
            qrimage.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();

        }

        view.findViewById(R.id.go_home_from_qrpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearBackStack();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new dashboardFragmentActivity()).commit();
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
