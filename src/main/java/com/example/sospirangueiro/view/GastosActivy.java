package com.example.sospirangueiro.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sospirangueiro.R;

import androidx.appcompat.app.AppCompatActivity;

public class GastosActivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        Button meta = findViewById(R.id.btnCriarMetas);
        Button gf = findViewById(R.id.btnRegistrarGastosFixos);

        meta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GastosActivy.this, MetaActivy.class);
                startActivity(intent);
            }
        });

        gf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GastosActivy.this, DespesasFixasActivy.class);
                startActivity(intent);
            }
        });
    }
}