package com.example.sospirangueiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.Gastos;
import com.example.sospirangueiro.modelbean.GastosFixos;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaDeGastosFixos extends RecyclerView.Adapter<ListaDeGastosFixos.ViewHolder> {
    List<GastosFixos> lista;
    Context context;

    public ListaDeGastosFixos(List<GastosFixos> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaDeGastosFixos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_modelo_gastos_fixos, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeGastosFixos.ViewHolder holder, int position) {
        GastosFixos gastosFixos = lista.get(position);
        holder.descricao.setText(gastosFixos.getDescricao());

        String real = new DecimalFormat("R$#,##0.00").format(
                gastosFixos.getValor());

        holder.valor.setText(real);

        holder.alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GastosFixos gf = new GastosFixos();
                gf.setId(gastosFixos.getId());
                gf.setDescricao(holder.descricao.getText().toString());
                gf.setValor(Double.parseDouble(holder.valor.getText().toString().substring(2).
                        replace(",", ".")));

                DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                dr.child("gastos_fixos").child(gf.getId()).setValue(gf);

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

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText descricao;
        private EditText valor;
        private ImageButton alterar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.descricao = itemView.findViewById(R.id.txtEditarDescricaoGF);
            this.valor = itemView.findViewById(R.id.txtEditarValorGF);
            this.alterar = itemView.findViewById(R.id.btnAlterarGastoFX);
        }

    }
}
