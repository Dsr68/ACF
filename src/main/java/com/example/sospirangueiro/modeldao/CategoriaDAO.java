package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.Categoria;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CategoriaDAO {
    public void inserirDados(Context context, String descricao){
        com.example.sospirangueiro.modelbean.Categoria c =
                new com.example.sospirangueiro.modelbean.Categoria();

        DatabaseReference dr = Inicializar.inicializar(context);

        c.setId(UUID.randomUUID().toString());
        c.setDescricao(descricao);

        dr.child("categoria").child(c.getId()).setValue(c);

    }

}
