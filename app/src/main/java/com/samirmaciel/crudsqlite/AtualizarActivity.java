package com.samirmaciel.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samirmaciel.crudsqlite.dao.UsuarioDAO;
import com.samirmaciel.crudsqlite.model.Usuario;

public class AtualizarActivity extends AppCompatActivity {
    private EditText inputNome, inputEmail;
    private Button btnAtualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        inputNome = findViewById(R.id.inputNomeAtt);
        inputEmail = findViewById(R.id.inputEmailAtt);
        btnAtualizar = findViewById(R.id.btnAtualizar);

        int id = getIntent().getIntExtra("usuarioID", 0);

        UsuarioDAO dao = new UsuarioDAO(this);
        Usuario usuario = dao.buscarUsuario(id);

        inputNome.setText(usuario.getNome());
        inputEmail.setText(usuario.getEmail());

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
                usuario.setNome(inputNome.getText().toString());
                usuario.setEmail(inputEmail.getText().toString());
                dao.atualizarUsuario(usuario);
                Toast.makeText(getApplicationContext(), "Usuario atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}