package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.GastosFixos;
import com.example.sospirangueiro.modelbean.Metas;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MetasDAO {
    public void inserirDados(Context context, String descricao, double valor, String tempo){
        Metas m = new Metas();
        m.setId(UUID.randomUUID().toString());
        m.setDescricao(descricao);
        m.setLimite(valor);
        m.setTempo(tempo);

        DatabaseReference dr = Inicializar.inicializar(context);

        dr.child("metas").child(m.getId()).setValue(m);

    }
}
