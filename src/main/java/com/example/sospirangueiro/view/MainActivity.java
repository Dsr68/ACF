package com.example.sospirangueiro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sospirangueiro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton gastos = findViewById(R.id.btnGastos);
        ImageButton despesas = findViewById(R.id.btnDespesasDiarias);
        ImageButton lista = findViewById(R.id.btnLista);
        ImageButton calcular = findViewById(R.id.btnCalcular);

        gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GastosActivy.class);
                startActivity(intent);
            }
        });

        despesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DespesasDiarias.class);
                startActivity(intent);
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listas.class);
                startActivity(intent);
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculos.class);
                startActivity(intent);
            }
        });
    }
}