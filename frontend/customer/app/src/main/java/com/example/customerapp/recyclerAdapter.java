package com.example.customerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerController> {

    private List<TransactionDetails> list;

    public recyclerAdapter(List<TransactionDetails> list){
        this.list=list;
    }


    @NonNull
    @Override
    public recyclerController onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new recyclerController(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerController holder, int position) {

        TransactionDetails transactionDetails=list.get(position);
        holder.tracnsId.setText(transactionDetails.getTransaction_id());
        holder.busId.setText(transactionDetails.getBus_id());
        holder.routeId.setText(transactionDetails.getRoute_id());
        holder.coh.setText(transactionDetails.getCount_of_head());
        holder.cph.setText(transactionDetails.getCost_per_head());
        holder.total.setText(transactionDetails.getTotal_cost());
        holder.date.setText(transactionDetails.getTransaction_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
