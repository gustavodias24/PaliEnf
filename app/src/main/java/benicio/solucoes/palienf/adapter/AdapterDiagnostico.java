package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.model.DiagnosticoModel;

public class AdapterDiagnostico extends RecyclerView.Adapter<AdapterDiagnostico.MyViewHolder> {
    List<DiagnosticoModel> diagnosticos;
    Activity c;

    public AdapterDiagnostico(List<DiagnosticoModel> diagnosticos, Activity c) {
        this.diagnosticos = diagnosticos;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_diagnostico, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DiagnosticoModel d = diagnosticos.get(position);
        holder.itemView.getRootView().setClickable(false);

        holder.titulo_diagnostico_text.setText(d.getNome());
        holder.descricao_diagnostico_text.setText(d.getDescricao());

        holder.botao_noc.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(c);
            // TODO
//            b.setView();
            b.create().show();
        });
    }

    @Override
    public int getItemCount() {
        return diagnosticos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo_diagnostico_text,descricao_diagnostico_text;
        Button botao_noc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo_diagnostico_text = itemView.findViewById(R.id.titulo_diagnostico_text);
            descricao_diagnostico_text = itemView.findViewById(R.id.descricao_diagnostico_text);
            botao_noc = itemView.findViewById(R.id.botao_noc);

        }
    }
}
