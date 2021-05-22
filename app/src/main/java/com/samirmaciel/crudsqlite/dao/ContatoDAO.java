package com.samirmaciel.crudsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samirmaciel.crudsqlite.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private SQLiteDatabase banco;

    public ContatoDAO(Context contexto) {
        Conexao conexao = new Conexao(contexto);
        banco = conexao.getWritableDatabase();
    }

    public long inserirContato(Contato contato){
        ContentValues values = new ContentValues();
        values.put("nome", contato.getNome());
        values.put("numero", contato.getNumero());

        return banco.insert("contatos", null, values);
    }

    public void excluirContato(Contato contato){
        banco.delete("contatos", "id == ?", new String[]{String.valueOf(contato.getId())});
    }

    public List<Contato> obterTodos(){
        Cursor cursor = banco.query("contatos", new String[]{"id", "nome", "numero"}, null, null, null, null, null);
        List<Contato> lista = new ArrayList<>();
        while(cursor.moveToNext()){
            Contato contato = new Contato(cursor.getString(1), cursor.getString(2));
            contato.setId(cursor.getInt(0));
            lista.add(contato);
        }

        return lista;
    }

    public Contato buscarContato(int id){
        Cursor cursor = banco.query("contatos", new String[]{"id", "nome", "numero"},null, null, null, null, null);
        while(cursor.moveToNext()){
            if(id == cursor.getInt(0)) {
                Contato contato = new Contato(cursor.getString(1), cursor.getString(2));
                contato.setId(cursor.getInt(0));
                return contato;
            }
        }

        return null;
    }

    public int atualizarUsuario(Contato contato){
        ContentValues values = new ContentValues();
        values.put("id", contato.getId());
        values.put("nome", contato.getNome());
        values.put("numero", contato.getNumero());

        return banco.update("contatos", values, "id == ?", new String[]{String.valueOf(contato.getId())});
    }
}
