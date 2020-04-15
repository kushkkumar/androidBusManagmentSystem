package com.example.conductorapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recyclerController extends RecyclerView.ViewHolder {
    TextView tid,custid,busid,routid,coh,cph,total,date;
    public recyclerController(@NonNull View itemView) {
        super(itemView);
        tid=itemView.findViewById(R.id.tid);
        busid=itemView.findViewById(R.id.bid);
        custid=itemView.findViewById(R.id.cutid);
        routid=itemView.findViewById(R.id.rid);
        coh=itemView.findViewById(R.id.coh);
        cph=itemView.findViewById(R.id.cph);
        total=itemView.findViewById(R.id.tot);
        date=itemView.findViewById(R.id.date);

    }
}
