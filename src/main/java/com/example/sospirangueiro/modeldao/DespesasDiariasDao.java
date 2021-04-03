package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DespesasDiariasDao {
    public void inserirDados(Context context, String descricao,
        Double valor, String categoria, String meta, String semana,
                             int mes, int ano){

        DespesasDiarias d = new DespesasDiarias();
                new com.example.sospirangueiro.modelbean.Categoria();

        DatabaseReference dr = Inicializar.inicializar(context);

        d.setId(UUID.randomUUID().toString());
        d.setDescricao(descricao);
        d.setValor(valor);
        d.setSemana(semana);
        d.setCategoria(categoria);
        d.setMeta(meta);
        d.setMes(mes);
        d.setAno(ano);

        dr.child("despesas").child(d.getId()).setValue(d);

    }

}
