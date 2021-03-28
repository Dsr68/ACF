package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.GastosFixos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DespesasFixasDAO {
    public void inserirDados(Context context, String descricao, double valor){
        GastosFixos g = new GastosFixos();
        g.setId(UUID.randomUUID().toString());
        g.setDescricao(descricao);
        g.setValor(valor);

        DatabaseReference dr = Inicializar.inicializar(context);

        dr.child("gastos_fixos").child(g.getId()).setValue(g);

    }
}
