package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import benicio.solucoes.palienf.CadastrarUsuarioActivity;
import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.model.UsuarioModel;

public class AdapterEnfermeiro extends RecyclerView.Adapter<AdapterEnfermeiro.MyViewHolder>{

    List<UsuarioModel> lista;
    Activity a;
    Dialog d;
    private DatabaseReference refUsuarios = FirebaseDatabase.getInstance().getReference().child("usuarios");

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

        holder.editarEnfermeiro.setOnClickListener( v -> {
            Intent i = new Intent(a, CadastrarUsuarioActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("payloadEdicao", usuarioModel);
            a.startActivity(i);
        });
        holder.excluirEnfermeiro.setOnClickListener( v -> {
            AlertDialog.Builder b = new AlertDialog.Builder(a);
            b.setTitle("Atenção!");
            b.setMessage("Tem certeza que deseja excluir essa informação para sempre?");
            b.setNegativeButton("Não", null);
            b.setPositiveButton("Sim", (dialogInterface,i) -> {
                refUsuarios.child(usuarioModel.getId()).setValue(null).addOnCompleteListener(task ->{
                    if ( task.isSuccessful() ){
                        d.dismiss();
                    }else{
                        Toast.makeText(a, "Tente Novamente...", Toast.LENGTH_SHORT).show();
                    }
                });
            });
            d = b.create();
            d.show();
        });
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
