package com.example.sospirangueiro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.Categoria;
import com.example.sospirangueiro.modelbean.Metas;
import com.example.sospirangueiro.modeldao.CategoriaDAO;
import com.example.sospirangueiro.modeldao.DespesasDiariasDao;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DespesasDiarias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesas_diarias);
        ArrayList<String> listaCategoria = new ArrayList<String>();
        ArrayList<String> listaMeta = new ArrayList<String>();

        EditText categoria = findViewById(R.id.txtCategoria);
        ImageButton adicionar = findViewById(R.id.btnCriarCategoria);
        //ImageButton salvar = findViewById(R.id.btnDespesasDiarias);

        Spinner semana = findViewById(R.id.spSemana);
        Spinner spCategoria = findViewById(R.id.spCategoria);
        Spinner meta = findViewById(R.id.spMeta);

        String semanas[] = getResources().getStringArray(R.array.semana);

        DatabaseReference drCategoria = FirebaseDatabase.getInstance().getReference(
                "categoria");
        drCategoria.orderByChild("descricao").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Categoria ct = snapshot.getValue(Categoria.class);
                listaCategoria.add(ct.getDescricao());

                spCategoria.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        listaCategoria));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference drMetas = FirebaseDatabase.getInstance().getReference("metas");
        drMetas.orderByChild("descricao").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Metas meta1 = snapshot.getValue(Metas.class);
                listaMeta.add(meta1.getDescricao());

                meta.setAdapter(new ArrayAdapter<String>(DespesasDiarias.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        listaMeta));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        EditText descricao = findViewById(R.id.txtDescricao);
        EditText valor = findViewById(R.id.txtValorDespesaDiaria);
        ImageButton registrar = findViewById(R.id.btnRegistrarGasto);

        semana.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                R.layout.support_simple_spinner_dropdown_item,
                semanas));

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriaDAO c = new CategoriaDAO();
                c.inserirDados(DespesasDiarias.this,
                        categoria.getText().toString());
                categoria.setText("");
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DespesasDiariasDao d = new DespesasDiariasDao();

                Date data = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(data);

                d.inserirDados(
                        getApplicationContext(),
                        descricao.getText().toString(),
                        Double.parseDouble(valor.getText().toString()),
                        spCategoria.getSelectedItem().toString(),
                        meta.getSelectedItem().toString(),
                        semana.getSelectedItem().toString(),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR)
                );

                descricao.setText("");
                valor.setText("");
            }
        });
    }
}