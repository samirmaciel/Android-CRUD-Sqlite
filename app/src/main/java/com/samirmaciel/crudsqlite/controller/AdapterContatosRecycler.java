package com.samirmaciel.crudsqlite.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.model.Contato;

import java.util.List;

public class AdapterContatosRecycler extends RecyclerView.Adapter<AdapterContatosRecycler.MyViewHolder>{

    private Context context;
    private List<Contato> contatos;

    public AdapterContatosRecycler(Context context, List<Contato> contatos) {

        this.context = context;
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater  = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contact_item, parent, false);



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