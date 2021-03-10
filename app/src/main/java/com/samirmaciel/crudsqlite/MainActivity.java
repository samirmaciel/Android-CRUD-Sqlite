package com.samirmaciel.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listaUsuarios;
    private List<Usuario> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaUsuarios = findViewById(R.id.listaUsuarios);

        UsuarioDAO dao = new UsuarioDAO(this);
        lista.addAll(dao.obterTodos());

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        listaUsuarios.setAdapter(adapter);
        registerForContextMenu(listaUsuarios);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        MenuItem lista = (MenuItem) menu.findItem(R.id.menuListar);
        lista.setEnabled(false);

        MenuItem cadastrar = (MenuItem) menu.findItem(R.id.menuCadastrar);
        cadastrar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
                return false;
            }
        });

        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);

        MenuItem excluir = (MenuItem) menu.findItem(R.id.contextoExcluir);

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo menuInfo =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                final Usuario usuario = lista.get(menuInfo.position);
                UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
                dao.excluirUsuario(usuario);
                lista.remove(menuInfo.position);
                listaUsuarios.invalidateViews();
                Toast.makeText(getApplicationContext(), "Usuario removido com sucesso!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        lista.clear();
        UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
        lista.addAll(dao.obterTodos());
        listaUsuarios.invalidateViews();
    }
}