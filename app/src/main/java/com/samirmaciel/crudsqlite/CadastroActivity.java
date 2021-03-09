package com.samirmaciel.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        MenuItem lista = (MenuItem) menu.findItem(R.id.menuListar);
        MenuItem cadastro = (MenuItem) menu.findItem(R.id.menuCadastrar);
        cadastro.setEnabled(false);

        lista.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(intent);
                return false;
            }
        });
        return true;
    }
}