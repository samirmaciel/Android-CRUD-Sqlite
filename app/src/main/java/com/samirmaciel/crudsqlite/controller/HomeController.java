package com.samirmaciel.crudsqlite.controller;

import android.content.Context;
import android.util.Log;
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

    public HomeController(HomeFragment fragment, Context context) {
        this.fragment = fragment;
        this.context = context;
        this.contatodao = new ContatoDAO(context);
    }

    public void getSaveView() {
        fragment.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new SaveFragment())
                .commit();
    }

    public void salvarContato(Contato contato){
        int insert = contatodao.atualizarUsuario(contato);
        if(insert == 1){
            Toast.makeText(context, "Contato salvo com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Falha ao salvar o contato!", Toast.LENGTH_SHORT).show();
        }
        Log.d("Retorno", "Número: " + insert );
    }
}
