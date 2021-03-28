package com.example.sospirangueiro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modeldao.DespesasFixasDAO;

public class DespesasFixasActivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_fixas_activy);

        EditText descricao = findViewById(R.id.txtDescricaoDF);
        EditText valor = findViewById(R.id.txtValorDF);
        ImageButton salvar = findViewById(R.id.btnSalvarDespesaFixa);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DespesasFixasDAO df = new DespesasFixasDAO();
                df.inserirDados(DespesasFixasActivy.this, descricao.getText().toString(),
                        Double.parseDouble(
                        valor.getText().toString()
                ));
                descricao.setText("");
                valor.setText("");
            }
        });
    }
}