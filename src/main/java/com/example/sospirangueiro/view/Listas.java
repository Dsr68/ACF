package com.example.sospirangueiro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.adapters.ListaDeCompras;
import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Listas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        List<DespesasDiarias> listaDespesasDiarias = new ArrayList<DespesasDiarias>();

        String listaMeses[] = getResources().getStringArray(R.array.meses);
        String listaSemanas[] = getResources().getStringArray(R.array.semana);

        Spinner meses = findViewById(R.id.spMes);
        Spinner semanas = findViewById(R.id.spSemanaLista);
        TextView ano = findViewById(R.id.txtAno);
        RecyclerView rv = findViewById(R.id.dados);
        ImageButton pesquisar = findViewById(R.id.btnPesquisar);
        ImageButton configurar = findViewById(R.id.btnConfig);

        meses.setAdapter(new ArrayAdapter<String>(Listas.this,
                R.layout.support_simple_spinner_dropdown_item,
                listaMeses));

        semanas.setAdapter(new ArrayAdapter<String>(Listas.this,
                R.layout.support_simple_spinner_dropdown_item,
                listaSemanas));

        Date data = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        System.out.println(calendar.get(Calendar.MONTH));

        ano.setText(Integer.toString(calendar.get(Calendar.YEAR)));
        int mes = calendar.get(Calendar.MONTH);

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("despesas");
        dr.orderByChild("ano").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DespesasDiarias dd = snapshot.getValue(DespesasDiarias.class);

                if(dd.getSemana().equals(semanas.getSelectedItem().toString()
                ) && dd.getMes() == convertStringPraNumero(meses.getSelectedItem().toString())
                 && dd.getAno() == Integer.parseInt(ano.getText().toString())){
                    listaDespesasDiarias.add(dd);

                    if(!(listaDespesasDiarias.equals(null))){
                        rv.setAdapter(new ListaDeCompras(listaDespesasDiarias,
                                getApplicationContext()));
                        rv.setLayoutManager(new LinearLayoutManager(
                                getApplicationContext()));
                    }
                }
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

        pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference("despesas");
                dr.orderByChild("ano").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        DespesasDiarias dd = snapshot.getValue(DespesasDiarias.class);

                        if(dd.getSemana().equals(semanas.getSelectedItem().toString()
                        ) && dd.getMes() == convertStringPraNumero(
                                meses.getSelectedItem().toString()
                        )
                                && dd.getAno() == Integer.parseInt(ano.getText().toString())){
                            listaDespesasDiarias.add(dd);

                            if(!(listaDespesasDiarias.equals(null))){
                                rv.setAdapter(new ListaDeCompras(listaDespesasDiarias,
                                        getApplicationContext()));
                                rv.setLayoutManager(new LinearLayoutManager(
                                        getApplicationContext()));
                            }
                        }
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
            }
        });

        configurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listas.this, TelaEditar.class);
                startActivity(intent);
            }
        });

    }

    public int convertStringPraNumero(String mes){
        int n = 0;
        switch (mes){
            case "Janeiro":
                n = 0;
                break;
            case "Fevereiro":
                n = 1;
                break;
            case "Março":
                n = 2;
                break;
            case "Abril":
                n = 3;
                break;
            case "Maio":
                n = 4;
                break;
            case "Junho":
                n = 5;
                break;
            case "Julho":
                n = 6;
                break;
            case "Agosto":
                n = 7;
                break;
            case "Setembro":
                n = 8;
                break;
            case "Outubro":
                n = 9;
                break;
            case "Novembro":
                n = 10;
                break;
            case "Dezembro":
                n = 11;
                break;
        }

        return n;
    }
}