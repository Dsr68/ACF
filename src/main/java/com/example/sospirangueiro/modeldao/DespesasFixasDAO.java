package com.example.sospirangueiro.modeldao;

import android.content.Context;
import android.util.Log;

import com.example.sospirangueiro.modelbean.Gastos;
import com.example.sospirangueiro.modelbean.GastosFixos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class DespesasFixasDAO {
    public void inserirDados(Context context, String descricao, double valor, int mes, int ano){
        GastosFixos g = new GastosFixos();
        g.setId(UUID.randomUUID().toString());
        g.setDescricao(descricao);
        g.setValor(valor);
        g.setMes(mes);
        g.setAno(ano);

        DatabaseReference dr = Inicializar.inicializar(context);

        dr.child("gastos_fixos").child(g.getId()).setValue(g);

    }

   public static int gerarSomaGastosFixos(DataSnapshot snapshot){
        int soma = 0;
        double somaReal = 0;

        for(DataSnapshot d : snapshot.child("gastos_fixos").getChildren()){
            GastosFixos g = d.getValue(GastosFixos.class);
            somaReal += g.getValor();
            System.out.println(somaReal);
        }

        return soma = (int) somaReal;
    }
}
