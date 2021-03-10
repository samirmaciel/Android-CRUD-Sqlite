package com.samirmaciel.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private Button btnSalvar;
    private EditText inputNome, inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnSalvar = findViewById(R.id.btnSalvar);
        inputEmail = findViewById(R.id.inputEmail);
        inputNome = findViewById(R.id.inputNome);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
                Usuario usuario = new Usuario(inputNome.getText().toString(), inputEmail.getText().toString());
                dao.inserirUsuario(usuario);
                Toast.makeText(getApplicationContext(), "Usuario " + usuario.getNome() + " salvo com sucesso!", Toast.LENGTH_SHORT).show();
                limparCampos();
            }
        });
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

    private void limparCampos(){
        inputNome.setText("");
        inputEmail.setText("");
    }
}