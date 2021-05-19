package com.samirmaciel.crudsqlite.controller;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.samirmaciel.crudsqlite.R;
import com.samirmaciel.crudsqlite.dao.ContatoDAO;
import com.samirmaciel.crudsqlite.model.Contato;
import com.samirmaciel.crudsqlite.view.HomeFragment;
import com.samirmaciel.crudsqlite.view.MainActivity;
import com.samirmaciel.crudsqlite.view.SaveFragment;

public class SaveController {

    private SaveFragment fragment;
    private ContatoDAO contatodao;
    private Context context;

    public SaveController (SaveFragment fragment, Context context){
        this.fragment = fragment;
        this.context = context;
        this.contatodao = new ContatoDAO(context);
    }

    public void salvarContato(){

        if(checkCampos()){
            contatodao.inserirContato(getContato());
        }

    }

    private boolean checkCampos(){
        if(!fragment.inputName.getText().toString().isEmpty() && !fragment.inputTelefone.getText().toString().isEmpty()){
            if(fragment.inputName.getText().toString().length() >= 6){
                if(fragment.inputTelefone.getText().toString().length() == 12){
                    return true;
                }else{
                    Toast.makeText(context, "Por favor preencha o número com no minimo 12 digitos!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(context, "Por favor preencha um nome com no minimo 6 letras!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(context, "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private Contato getContato(){
        Contato contato = new Contato(fragment.inputName.getText().toString(), fragment.inputTelefone.getText().toString());
        return contato;
    }

    public void getHomeFragment(){
        fragment.getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, new HomeFragment())
                .commit();
    }


}
