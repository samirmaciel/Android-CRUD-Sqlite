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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.common.WindowType;
import com.samirmaciel.crudsqlite.common.AdapterContatosRecycler;
import com.samirmaciel.crudsqlite.controller.HomeController;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;

import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recycler;
    private List<Contato> contatos;
    private ContatoDAO contatodao;
    public HomeController controller;
    private FloatingActionButton btnAdd;
    private AdapterContatosRecycler adapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recycler = (RecyclerView) view.findViewById(R.id.recyclerContatos);
        contatodao = new ContatoDAO(getActivity().getApplicationContext());
        btnAdd = (FloatingActionButton) view.findViewById(R.id.btnAdd);

        contatos = contatodao.obterTodos();

        adapter = new AdapterContatosRecycler(this, getActivity().getApplicationContext(), contatos, getActivity());
        controller = new HomeController(this, getActivity().getApplicationContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputView(null, WindowType.REGISTRAR);
            }
        });

        return view;
    }

    public void showInputView(Contato contato, WindowType type) {

        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_view, null);

        EditText inputNome = (EditText) view.findViewById(R.id.inputNameEdit);
        EditText inputTelefone = (EditText) view.findViewById(R.id.inputNumberEdit);
        Button btnSalvar = (Button) view.findViewById(R.id.btnSaveEdit);
        Button btnFechar = (Button) view.findViewById(R.id.btnFecharEdit);
        TextView titulo = (TextView) view.findViewById(R.id.title);



        inputTelefone.addTextChangedListener(new TextWatcher() {
            String ultimoCaracter = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int tamanhInputTelefone = inputTelefone.getText().toString().length();
                if (tamanhInputTelefone > 1) {
                    ultimoCaracter = inputTelefone.getText().toString().substring(tamanhInputTelefone - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int tamanhoInputTelefone = inputTelefone.getText().toString().length();
                if (tamanhoInputTelefone == 2) {
                    if (!ultimoCaracter.equals(" ")) {
                        inputTelefone.append(" ");
                    } else {
                        inputTelefone.getText().delete(tamanhoInputTelefone - 1, tamanhoInputTelefone - 1);
                    }
                } else if (tamanhoInputTelefone == 8) {
                    if (!ultimoCaracter.equals(" ")) {
                        inputTelefone.append(" ");
                    } else {
                        inputTelefone.getText().delete(tamanhoInputTelefone - 1, tamanhoInputTelefone - 1);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (type.equals(WindowType.REGISTRAR)) {
            titulo.setText("ADICIONAR CONTATO");
        }else{
            inputNome.setText(contato.getNome());
            inputTelefone.setText(contato.getNumero());
        }


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals(WindowType.EDITAR)) {
                    contato.setNome(inputNome.getText().toString());
                    contato.setNumero(inputTelefone.getText().toString());
                    controller.editarContato(contato);
                    dialog.dismiss();
                    adapter.notifyDataSetChanged();
                } else {
                    if (!inputNome.getText().toString().isEmpty() && !inputTelefone.getText().toString().isEmpty()) {
                        if (inputNome.getText().toString().length() >= 6) {
                            if (inputTelefone.getText().toString().length() == 13) {
                                Contato contato1 = new Contato(inputNome.getText().toString(), inputTelefone.getText().toString());
                                controller.salvarContato(contato1);
                                contatos.add(contato1);
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(), "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Por favor preencha o número com no minimo 12 digitos!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Por favor preencha um nome com no minimo 6 letras!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(view);
        dialog.show();


    }

}