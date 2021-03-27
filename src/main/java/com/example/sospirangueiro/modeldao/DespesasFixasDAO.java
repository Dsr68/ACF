package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.GastosFixos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DespesasFixasDAO {
    public FirebaseDatabase fd;
    public  DatabaseReference dr;

    public void inicilalizar(Context contexto){
        FirebaseApp.initializeApp(contexto);
        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference();
    }
    public void inserirDados(String descricao, double valor){
        GastosFixos g = new GastosFixos();
        g.setId(UUID.randomUUID().toString());
        g.setDescricao(descricao);
        g.setValor(valor);

        dr.child("gastos_fixos").child(g.getId()).setValue(g);

    }
}
