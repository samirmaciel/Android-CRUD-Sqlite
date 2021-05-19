package com.samirmaciel.crudsqlite.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {
    private final static String name = "banco.db";
    private final static int version = 3;

    public Conexao(Context contexto) {

        super(contexto, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_contatos = "create table contatos (id integer primary key autoincrement, nome string, numero string);";
        db.execSQL(sql_contatos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuarios;");
        onCreate(db);
    }
}
