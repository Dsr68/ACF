package com.example.sospirangueiro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.adapters.ListaDeGastosFixos;
import com.example.sospirangueiro.adapters.ListaDeMetas;
import com.example.sospirangueiro.modelbean.Gastos;
import com.example.sospirangueiro.modelbean.GastosFixos;
import com.example.sospirangueiro.modelbean.Metas;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TelaEditar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_editar);

        List<Metas> lista = new ArrayList<Metas>();
        List<GastosFixos> lsGastos = new ArrayList<GastosFixos>();

        Button metas = findViewById(R.id.btnEditarMetas);
        Button gastos = findViewById(R.id.btnEditarGastosFixos);
        RecyclerView rView = findViewById(R.id.rEditar);

        metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lista.clear();

                        for(DataSnapshot ds1 : snapshot.child("metas").getChildren()){
                            Metas m = ds1.getValue(Metas.class);
                            lista.add(m);

                            rView.setAdapter(new ListaDeMetas(lista, getApplicationContext()));
                            rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        gastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        lsGastos.clear();
                        for(DataSnapshot ds2 : snapshot.child("gastos_fixos").getChildren()){
                            GastosFixos gf = ds2.getValue(GastosFixos.class);
                            lsGastos.add(gf);

                        }
                        rView.setAdapter(new ListaDeGastosFixos(lsGastos, getApplicationContext()
                        ));
                        rView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}