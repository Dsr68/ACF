package com.example.sospirangueiro.modeldao;

import android.content.Context;

import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.DatabaseReference;

import java.util.UUID;

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
