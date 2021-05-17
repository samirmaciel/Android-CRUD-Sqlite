package com.samirmaciel.crudsqlite.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private final static String name = "banco.db";
    private final static int version = 1;

    public Conexao(Context contexto) {

        super(contexto, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_usuarios = "CREATE TABLE usuarios (id integer primary key autoincrement, nome string, email string);";
        db.execSQL(sql_usuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
