package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.Metas;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

public class Categoria {
    public void inserirDados(Context context, String descricao){
        com.example.sospirangueiro.modelbean.Categoria c =
                new com.example.sospirangueiro.modelbean.Categoria();

        DatabaseReference dr = Inicializar.inicializar(context);

        c.setId(UUID.randomUUID().toString());
        c.setDescricao(descricao);

        dr.child("categoria").child(c.getId()).setValue(c);

    }
}
