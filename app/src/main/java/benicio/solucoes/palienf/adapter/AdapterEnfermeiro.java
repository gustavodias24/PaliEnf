package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.model.UsuarioModel;

public class AdapterEnfermeiro extends RecyclerView.Adapter<AdapterEnfermeiro.MyViewHolder>{

    List<UsuarioModel> lista;
    Activity a;

    public AdapterEnfermeiro(List<UsuarioModel> lista, Activity a) {
        this.lista = lista;
        this.a = a;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_enfermeiro, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UsuarioModel usuarioModel  = lista.get(position);

        holder.infos.setText(usuarioModel.toString());

        holder.editarEnfermeiro.setOnClickListener( v -> Toast.makeText(a, "Ainda não fiz...", Toast.LENGTH_SHORT).show());
        holder.excluirEnfermeiro.setOnClickListener( v -> Toast.makeText(a, "Ainda não fiz...", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView infos;
        Button editarEnfermeiro, excluirEnfermeiro;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            infos = itemView.findViewById(R.id.textInfosEnfermeiros);
            editarEnfermeiro = itemView.findViewById(R.id.editarEnfermeiro);
            excluirEnfermeiro = itemView.findViewById(R.id.excluirEnfermeiro);
        }
    }
}
