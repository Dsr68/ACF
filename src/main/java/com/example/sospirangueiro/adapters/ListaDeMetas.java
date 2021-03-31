package com.example.sospirangueiro.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sospirangueiro.R;
import com.example.sospirangueiro.modelbean.Metas;
import com.example.sospirangueiro.modeldao.MetasDAO;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListaDeMetas extends RecyclerView.Adapter<ListaDeMetas.ViewHolder> {
    private List<Metas> lista;
    private Context context;

    public ListaDeMetas(List<Metas> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @NonNull
    @Override
    public ListaDeMetas.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_modelo_editar_metas, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeMetas.ViewHolder holder, int position) {
        /*System.out.println(lista);
        for (Metas m : lista){
            System.out.println(m.getTempo());
        }*/

        Metas meta = lista.get(position);
        holder.descricao.setText(meta.getDescricao());

        String valor = new DecimalFormat("R$ #,##0.00").format(
                meta.getLimite()
        );

        holder.limite.setText(valor);

        String palavras[] = context.getResources().getStringArray(R.array.semana);

        holder.tempo.setAdapter(new ArrayAdapter<String>(context,
                R.layout.support_simple_spinner_dropdown_item,
                palavras));

        holder.alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conversao = holder.limite.getText().toString().substring(2);

                Metas m = new Metas();
                m.setId(meta.getId());
                m.setDescricao(holder.descricao.getText().toString());
                m.setLimite(Double.valueOf(conversao.replace(",", ".")));
                m.setTempo(holder.tempo.getSelectedItem().toString());

                DatabaseReference mr = FirebaseDatabase.getInstance().getReference();
                mr.child("metas").child(m.getId()).setValue(m);

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
        private EditText limite;
        private Spinner tempo;
        private ImageButton alterar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.descricao = itemView.findViewById(R.id.txtEditarNomeMeta);
            this.limite = itemView.findViewById(R.id.txtEditarLimite);
            this.tempo = itemView.findViewById(R.id.spEditarTempoMeta);
            this.alterar = itemView.findViewById(R.id.btnAlterar);
        }
    }
}
