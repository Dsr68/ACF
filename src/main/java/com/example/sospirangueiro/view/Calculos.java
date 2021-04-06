
package com.example.sospirangueiro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.Categoria;
import com.example.sospirangueiro.modelbean.GastosFixos;
import com.example.sospirangueiro.modelbean.Metas;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.*;

public class Calculos extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calculos);

            TextView gf = findViewById(R.id.txtViewGastoFixo);
            gf.setText("Gastos Fixos");
            TextView dv = findViewById(R.id.txtViewDespesasVariaveis);
            dv.setText("Despesas Variáveis");
            TextView gastosFixo = findViewById(R.id.txtViewValorGastoFixo);
            TextView gastosVariaveis = findViewById(R.id.txtViewValorGV);
            TextView percentualGF = findViewById(R.id.txtPGF);
            TextView pecentualDV = findViewById(R.id.txtPDV);
            ProgressBar pGF =  findViewById(R.id.pGF);
            ProgressBar pDV = findViewById(R.id.pDV);

            //Observação: onde tem categoria na verdade é metas
            TextView valorDespesas = findViewById(R.id.txtViewValorCategoria);
            ProgressBar progressBarCategoria = findViewById(R.id.pCategoria);
            TextView percentagemCategoria = findViewById(R.id.txtPC);
            Spinner pesquisa = findViewById(R.id.spPesquisarCategoria);
            ImageButton btnPesquisa = findViewById(R.id.btnPesquisaCategoria);

            DatabaseReference dr1 = FirebaseDatabase.getInstance().getReference("despesas");
            dr1.orderByValue().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    DespesasDiarias dd = snapshot.getValue(DespesasDiarias.class);

                    Date data = new Date();
                    Calendar calendar = getInstance();
                    calendar.setTime(data);

                    NumberFormat format = NumberFormat.getCurrencyInstance();

                    if(calendar.get(MONTH) == dd.getMes()){
                        double convecao =  Double.parseDouble(gastosVariaveis.getText().toString().
                                substring(2).replace(",", "."));
                        gastosVariaveis.setText(format.format(convecao + dd.getValor()));
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

            DatabaseReference dr2 = FirebaseDatabase.getInstance().getReference("gastos_fixos");
            dr2.orderByValue().addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    GastosFixos gastosFixos = snapshot.getValue(GastosFixos.class);

                    Date data = new Date();
                    Calendar calendar = getInstance();
                    calendar.setTime(data);

                    NumberFormat format = NumberFormat.getCurrencyInstance();

                    if((gastosFixos.getMes() == calendar.get(MONTH))){
                        double valor = Double.parseDouble(gastosFixo.getText().toString().
                                substring(2).replace(",", "."));
                        gastosFixo.setText(format.format(valor + gastosFixos.getValor()));
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

           DatabaseReference dr3 = FirebaseDatabase.getInstance().getReference();
            dr3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    double valorGF = 0;
                    double valorDV = 0;
                    double percentual1 = 0;
                    double percentual2 = 0;

                    for(DataSnapshot ds : snapshot.child("gastos_fixos").getChildren()){
                        GastosFixos gastosFixos = ds.getValue(GastosFixos.class);
                        valorGF += gastosFixos.getValor();
                    }

                    int convecao1 = 0;

                    for(DataSnapshot d : snapshot.child("despesas").getChildren()){
                        DespesasDiarias dv = d.getValue(DespesasDiarias.class);
                        valorDV += dv.getValor();
                    }

                    percentual1 = ((valorGF * 100)/(valorGF + valorDV));
                    convecao1 = (int) percentual1;

                    percentualGF.setText(Integer.toString(convecao1) + "%");
                    pGF.setMax(100);
                    pGF.setProgress(convecao1);

                    int convecao2 = 0;

                    percentual2 = ((valorDV * 100)/(valorGF + valorDV));
                    convecao2 = (int) percentual2;

                    pecentualDV.setText(Integer.toString(convecao2) + "%");
                    pDV.setMax(100);
                    pDV.setProgress(convecao2);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        DatabaseReference dr4 = FirebaseDatabase.getInstance().getReference();
        dr4.addValueEventListener(new ValueEventListener() {
            List<String> lista = new ArrayList<String>();
            double totDespesas = 0;
            double percentual = 0;
            int convercao = 0;
            Metas metas = new Metas();
            DespesasDiarias despesasDiarias = new DespesasDiarias();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                lista.clear();

                for(DataSnapshot ds : snapshot.child("metas").getChildren()){
                    metas = ds.getValue(Metas.class);
                    lista.add(metas.getDescricao());
                }

                pesquisa.setAdapter(new ArrayAdapter<String>(Calculos.this,
                        R.layout.support_simple_spinner_dropdown_item,
                        lista));


                Date data = new Date();
                Calendar calendar = getInstance();
                calendar.setTime(data);

                for(DataSnapshot ds2 : snapshot.child("despesas").getChildren()){
                    despesasDiarias = ds2.getValue(DespesasDiarias.class);

                    if(calendar.get(MONTH) == despesasDiarias.getMes() &&
                    calendar.get(YEAR) == despesasDiarias.getAno()
                    ){
                        if( (pesquisa.getSelectedItem().toString().equals(
                                despesasDiarias.getMeta()
                        ))){
                            System.out.println(despesasDiarias.getDescricao());
                            totDespesas += despesasDiarias.getValor();
                        }

                    }
                }

                NumberFormat format = NumberFormat.getCurrencyInstance();
                String convertido = format.format(totDespesas);
                valorDespesas.setText(convertido);

                for(DataSnapshot ds2 : snapshot.child("despesas").getChildren()){
                    despesasDiarias = ds2.getValue(DespesasDiarias.class);

                    if(calendar.get(MONTH) == despesasDiarias.getMes() &&
                    calendar.get(YEAR) == despesasDiarias.getAno() &&
                    pesquisa.getSelectedItem().toString().equals(despesasDiarias.getMeta())){
                        for(DataSnapshot ds3 : snapshot.child("metas").getChildren()){
                            Metas metas1 = ds3.getValue(Metas.class);
                            if(pesquisa.getSelectedItem().toString().equals(metas1.getDescricao())){
                                percentual = (totDespesas * 100)/(metas1.getLimite());
                                convercao = (int) percentual;

                                progressBarCategoria.setMax(100);
                                progressBarCategoria.setProgress(convercao);

                                percentagemCategoria.setText(convercao + "%");
                            }
                        }
                    }
                }

                btnPesquisa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double totDespesas2 = 0;
                        for(DataSnapshot ds2 : snapshot.child("despesas").getChildren()){
                            despesasDiarias = ds2.getValue(DespesasDiarias.class);

                            if(calendar.get(MONTH) == despesasDiarias.getMes() &&
                                    calendar.get(YEAR) == despesasDiarias.getAno()
                            ){
                                if( (pesquisa.getSelectedItem().toString().equals(
                                        despesasDiarias.getMeta()
                                ))){
                                    System.out.println(despesasDiarias.getDescricao());
                                    totDespesas2 += despesasDiarias.getValor();
                                }

                            }
                        }

                        for(DataSnapshot ds3 : snapshot.child("metas").getChildren()){
                            Metas metas1 = ds3.getValue(Metas.class);
                            if(pesquisa.getSelectedItem().toString().equals(metas1.getDescricao())){
                                percentual = (totDespesas2 * 100)/(metas1.getLimite());
                                convercao = (int) percentual;

                                String convertido2 = format.format(totDespesas2);
                                valorDespesas.setText(convertido2);

                                progressBarCategoria.setMax(100);
                                progressBarCategoria.setProgress(convercao);

                                percentagemCategoria.setText(convercao + "%");
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        }
}