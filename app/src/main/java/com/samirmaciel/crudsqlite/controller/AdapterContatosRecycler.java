package com.samirmaciel.crudsqlite.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;
import com.samirmaciel.crudsqlite.view.HomeFragment;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterContatosRecycler extends RecyclerView.Adapter<AdapterContatosRecycler.MyViewHolder>{

    private Context context;
    private List<Contato> contatos;
    private ContatoDAO contatodao;
    private HomeFragment fragment;

    public AdapterContatosRecycler(HomeFragment fragment, Context context, List<Contato> contatos) {
        this.contatodao = new ContatoDAO(context);
        this.fragment = fragment;
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
        holder.nome.setText(contatos.get(position).getNome());
        holder.numero.setText(contatos.get(position).getNumero());

        holder.btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contatos.remove(position);
                contatodao.excluirContato(contatos.get(position));
                notifyDataSetChanged();
            }
        });

        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.showEditView(contatos.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {

        return contatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome, numero;
        ImageButton btnEditar, btnDeletar;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            nome = itemView.findViewById(R.id.textName);
            numero = itemView.findViewById(R.id.textNumber);
            btnEditar = itemView.findViewById(R.id.btnEdit);
            btnDeletar = itemView.findViewById(R.id.btnDelete);
        }


    }


}