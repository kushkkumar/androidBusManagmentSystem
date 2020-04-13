package com.example.customerapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerController extends RecyclerView.ViewHolder {
    TextView tracnsId,busId,routeId,coh,cph,total,date;


    public recyclerController(@NonNull View itemView) {
        super(itemView);
        tracnsId=itemView.findViewById(R.id.tid);
        busId=itemView.findViewById(R.id.bid);
        routeId=itemView.findViewById(R.id.rid);
        coh=itemView.findViewById(R.id.coh);
        cph=itemView.findViewById(R.id.cph);
        total=itemView.findViewById(R.id.tot);
        date=itemView.findViewById(R.id.date);
    }
}
