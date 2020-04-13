package com.example.customerapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class dashboardFragmentActivity extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dashbord_fragment,container,false);

        view.findViewById(R.id.add_money_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new addMoneyFragmentActivity()).addToBackStack(null).commit();

            }
        });

        view.findViewById(R.id.show_qr_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new qrCodeDetailFragmentActivity()).addToBackStack(null).commit();

            }
        });

        view.findViewById(R.id.payments_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fram1,new paymentDetailFragmentActivity()).addToBackStack(null).commit();


            }
        });

        return view;
    }
}
