package com.samirmaciel.crudsqlite.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.model.Sector;

import java.util.List;

public class ServicesAdapterRecycler extends RecyclerView.Adapter<ServicesAdapterRecycler.MyViewHolder>{

    private Context context;
    private List<Sector> sectors;

    public ServicesAdapterRecycler(Context context, List<Sector> sectors) {

        this.context = context;
        this.sectors = sectors;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_service_item, parent, false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
