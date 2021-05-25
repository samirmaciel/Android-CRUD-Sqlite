package com.samirmaciel.crudsqlite.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;
import com.samirmaciel.crudsqlite.view.HomeFragment;
import com.samirmaciel.crudsqlite.view.SaveFragment;

public class HomeController {

    private HomeFragment fragment;
    private Context context;
    private ContatoDAO contatodao;
    private Activity activity;
    private AdapterContatosRecycler adapter;

    public HomeController(HomeFragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
        this.contatodao = new ContatoDAO(context);
        this.adapter = adapter;
        this.activity = activity;
    }

    public void getSaveView() {
        fragment.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new SaveFragment())
                .commit();
    }

    public void editarContato(Contato contato){
        int insert = contatodao.atualizarUsuario(contato);
        if(insert == 1){
            Toast.makeText(context, "Contato editado com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Falha ao salvar o contato!", Toast.LENGTH_SHORT).show();
        }
        Log.d("Retorno", "Número: " + insert );
    }

    public void salvarContato(Contato contato){
        contatodao.inserirContato(contato);
    }




}
