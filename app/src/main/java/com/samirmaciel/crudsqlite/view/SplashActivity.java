package com.samirmaciel.crudsqlite.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.samirmaciel.crudsqlite.R;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textView = findViewById(R.id.textView);

        textView.setText("● Adiconar novos contatos\n" +
                "● Excluir contatos\n" +
                "● Editar contatos");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }, 5000);
    }
}