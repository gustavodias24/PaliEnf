package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.model.PacienteModel;

public class AdapterPaciente extends RecyclerView.Adapter<AdapterPaciente.MyViewHolder> {

    List<PacienteModel> pacientes;
    Activity c;

    public AdapterPaciente(List<PacienteModel> pacientes, Activity c) {
        this.pacientes = pacientes;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_paciente, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomePaciente.setText(pacientes.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomePaciente;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomePaciente = itemView.findViewById(R.id.nome_paciente);
        }
    }
}
