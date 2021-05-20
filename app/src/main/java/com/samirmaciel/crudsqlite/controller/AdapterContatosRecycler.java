package com.samirmaciel.crudsqlite.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterContatosRecycler extends RecyclerView.Adapter<AdapterContatosRecycler.MyViewHolder>{

    private Context context;
    private List<Contato> contatos;
    private ContatoDAO contatodao;

    public AdapterContatosRecycler(Context context, List<Contato> contatos) {
        this.contatodao = new ContatoDAO(context);
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