package com.example.sospirangueiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.Categoria;
import com.example.sospirangueiro.modelbean.DespesasDiarias;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class ListaDeCompras extends RecyclerView.Adapter<ListaDeCompras.ViewHolder>{
    private List<DespesasDiarias> lista;
    private Context context;

    public ListaDeCompras(List<DespesasDiarias> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaDeCompras.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.modelolistacompras, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeCompras.ViewHolder holder, int position) {
        DespesasDiarias despesas = lista.get(position);

        String valor = new DecimalFormat("R$ #,##0.00").format(
                despesas.getValor()
        );

        List<String> categorias = new ArrayList<String>();
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("categoria");
        dr.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Categoria c = snapshot.getValue(Categoria.class);
                categorias.add(c.getDescricao());

                holder.categoria.setAdapter(new ArrayAdapter<String>(context,
                        R.layout.support_simple_spinner_dropdown_item,
                        categorias));
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

        holder.descricao.setText(despesas.getDescricao());
        holder.valor.setText(valor);
        holder.botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                DespesasDiarias dd = new DespesasDiarias();
                dd.setId(despesas.getId());
                dd.setDescricao(holder.descricao.getText().toString());
                dd.setCategoria(holder.categoria.getSelectedItem().toString());
                dd.setValor(Double.parseDouble(holder.valor.getText().toString().
                        substring(2).replace(",", ".")));
                dd.setMes(despesas.getMes());
                dd.setAno(despesas.getAno());
                dd.setSemana(despesas.getSemana());

                dr.child("despesas").child(dd.getId()).setValue(dd);

                Toast toast = Toast.makeText(context, "Alteração realizada com sucesso",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView descricao;
        private final TextView valor;
        private final Spinner categoria;
        private final ImageButton botao;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.descricao = view.findViewById(R.id.txtModeloDescricao);
            this.valor = view.findViewById(R.id.txtModeloValor);
            this.categoria = view.findViewById(R.id.spViewCategorias);
            this.botao = view.findViewById(R.id.btnModeloView);
        }

        public TextView getDescricao() {
            return descricao;
        }

        public TextView getValor() {
            return valor;
        }

        public ImageButton getBotao() {
            return botao;
        }


    }
}
