package com.example.sospirangueiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.DespesasDiarias;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
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

        holder.descricao.setText(despesas.getDescricao());
        holder.valor.setText(valor);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView descricao;
        private final TextView valor;
        private final ImageButton botao;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.descricao = view.findViewById(R.id.txtModeloDescricao);
            this.valor = view.findViewById(R.id.txtModeloValor);
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
