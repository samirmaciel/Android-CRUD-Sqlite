package com.samirmaciel.crudsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.samirmaciel.crudsqlite.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private SQLiteDatabase banco;

    public UsuarioDAO(Context contexto) {
        Conexao conexao = new Conexao(contexto);
        banco = conexao.getWritableDatabase();
    }

    public void inserirUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());

        long num = banco.insert("usuarios", null, values);
    }

    public void excluirUsuario(Usuario usuario){
        banco.delete("usuarios", "id == ?", new String[]{String.valueOf(usuario.getId())});
    }

    public List<Usuario> obterTodos(){
        Cursor cursor = banco.query("usuarios", new String[]{"id", "nome", "email"}, null, null, null, null, null);
        List<Usuario> lista = new ArrayList<>();
        while(cursor.moveToNext()){
            Usuario usuario = new Usuario(cursor.getString(1), cursor.getString(2));
            usuario.setId(cursor.getInt(0));
            lista.add(usuario);
        }

        return lista;
    }

    public Usuario buscarUsuario(int id){
        Cursor cursor = banco.query("usuarios", new String[]{"id", "nome", "email"},null, null, null, null, null);
        while(cursor.moveToNext()){
            if(id == cursor.getInt(0)) {
                Usuario usuario = new Usuario(cursor.getString(1), cursor.getString(2));
                usuario.setId(cursor.getInt(0));
                return usuario;
            }
        }

        return null;
    }

    public void atualizarUsuario(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("id", usuario.getId());
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());

        int num = banco.update("usuarios", values, "id == ?", new String[]{String.valueOf(usuario.getId())});
    }
}
