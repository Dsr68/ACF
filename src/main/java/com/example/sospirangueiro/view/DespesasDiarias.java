package com.example.sospirangueiro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modeldao.Categoria;

public class DespesasDiarias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_diarias);

        EditText categoria = findViewById(R.id.txtCategoria);
        ImageButton adicionar = findViewById(R.id.btnCriarCategoria);
        ImageButton salvar = findViewById(R.id.btnDespesasDiarias);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Categoria c = new Categoria();
                c.inserirDados(DespesasDiarias.this,
                        categoria.getText().toString());
                categoria.setText("");
            }
        });
    }
}