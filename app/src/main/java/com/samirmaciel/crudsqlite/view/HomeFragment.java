package com.samirmaciel.crudsqlite.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.controller.AdapterContatosRecycler;
import com.samirmaciel.crudsqlite.controller.HomeController;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;

import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recycler;
    private List<Contato> contatos;
    private ContatoDAO contatodao;
    private HomeController controller;
    private FloatingActionButton btnAdd;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        controller = new HomeController(this, getActivity().getApplicationContext());
        recycler = (RecyclerView) view.findViewById(R.id.recyclerContatos);
        contatodao = new ContatoDAO(getActivity().getApplicationContext());
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.getSaveView();
            }
        });


        contatos = contatodao.obterTodos();

        System.out.println(contatos);

        AdapterContatosRecycler adapter = new AdapterContatosRecycler(this, getActivity().getApplicationContext(), contatos);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));



        return view;
    }


    public void showEditView(Contato contato){

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_view, null);

        EditText inputNome = (EditText) view.findViewById(R.id.inputNameEdit);
        EditText inputTelefone = (EditText) view.findViewById(R.id.inputNumberEdit);
        Button btnSalvar = (Button) view.findViewById(R.id.btnSaveEdit);
        Button btnFechar = (Button) view.findViewById(R.id.btnFecharEdit);

        inputNome.setText(contato.getNome());
        inputTelefone.setText(contato.getNumero());

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.setContentView(view);
        dialog.show();
    }
}