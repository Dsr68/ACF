
package com.example.sospirangueiro.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.GastosFixos;
import com.example.sospirangueiro.modeldao.DespesasFixasDAO;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

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
        }
}