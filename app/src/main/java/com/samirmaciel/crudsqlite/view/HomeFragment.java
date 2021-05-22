package com.samirmaciel.crudsqlite.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
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
    private AdapterContatosRecycler adapter;


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

        adapter = new AdapterContatosRecycler(this, getActivity().getApplicationContext(), contatos);
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

        inputTelefone.addTextChangedListener(new TextWatcher() {
            String ultimoCaracter = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int tamanhInputTelefone = inputTelefone.getText().toString().length();
                if(tamanhInputTelefone > 1){
                    ultimoCaracter = inputTelefone.getText().toString().substring(tamanhInputTelefone - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int tamanhoInputTelefone = inputTelefone.getText().toString().length();
                if (tamanhoInputTelefone == 2){
                    if (!ultimoCaracter.equals(" ")){
                        inputTelefone.append(" ");
                    }else{
                        inputTelefone.getText().delete(tamanhoInputTelefone - 1, tamanhoInputTelefone - 1);
                    }
                } else if (tamanhoInputTelefone == 8){
                    if (!ultimoCaracter.equals(" ")){
                        inputTelefone.append(" ");
                    }else {
                        inputTelefone.getText().delete(tamanhoInputTelefone - 1, tamanhoInputTelefone - 1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputNome.setText(contato.getNome());
        inputTelefone.setText(contato.getNumero());



        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contato.setNome(inputNome.getText().toString());
                contato.setNumero(inputTelefone.getText().toString());
                controller.salvarContato(contato);
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });

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