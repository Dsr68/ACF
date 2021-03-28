package com.example.sospirangueiro.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modeldao.MetasDAO;

public class MetaActivy extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta_activy);

        Spinner tempo = findViewById(R.id.spTempo);
        String lista[] = getResources().getStringArray(R.array.tempo);
        ImageButton salvar = findViewById(R.id.btnCriarMeta);
        EditText descricao = findViewById(R.id.txtdescricao);
        EditText valor = findViewById(R.id.txtValor);

        tempo.setAdapter(new ArrayAdapter<String>(getApplicationContext()
        , R.layout.support_simple_spinner_dropdown_item, lista));

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t = tempo.getSelectedItem().toString();
                MetasDAO m = new MetasDAO();

                m.inserirDados(MetaActivy.this,
                        descricao.getText().toString(),
                        Double.parseDouble(valor.getText().toString()),
                        t
                        );
                descricao.setText("");
                valor.setText("");
            }
        });
    }
}