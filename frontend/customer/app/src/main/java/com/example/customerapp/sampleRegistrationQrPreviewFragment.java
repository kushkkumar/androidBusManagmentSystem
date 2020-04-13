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

import retrofit2.Retrofit;

public class sampleRegistrationQrPreviewFragment extends Fragment {

    TextView user,bal;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.sample_registration_qr_preview_fragment,container,false);

        user=view.findViewById(R.id.qrCustUserId);
        bal=view.findViewById(R.id.qrBalId);



        ImageView barcode=view.findViewById(R.id.registerPreview);
        String data=setterRegisterPreview.getCust_user_name();
        String bala=setterRegisterPreview.getBalance();

        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();



        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(data, BarcodeFormat.QR_CODE,200,200); //encode the string
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);  //conver into the qr code
            barcode.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();

        }

        user.setText(data);
        bal.setText(bala);

        view.findViewById(R.id.homeBtnToDashBord).setOnClickListener(new View.OnClickListener() {
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
