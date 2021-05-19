package com.samirmaciel.crudsqlite.view;

import android.os.Bundle;

import android.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.controller.AdapterContatosRecycler;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;

import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recycler;
    private List<Contato> contatos;
    private ContatoDAO contatodao;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recycler = (RecyclerView) view.findViewById(R.id.recyclerContatos);
        contatodao = new ContatoDAO(getActivity().getApplicationContext());

        contatos = contatodao.obterTodos();

        AdapterContatosRecycler adapter = new AdapterContatosRecycler(getContext(), contatos);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));



        return view;
    }
}