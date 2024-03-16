package benicio.solucoes.palienf.adapter;

import static benicio.solucoes.palienf.utils.PDFGenerator.generateAndSharePDF;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import benicio.solucoes.palienf.DiagnosticoEnfermagemActivity;
import benicio.solucoes.palienf.IntervencoesActivity;
import benicio.solucoes.palienf.NovoPacienteActivity;
import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.RelatorioDiarioActivity;
import benicio.solucoes.palienf.model.AvaDiariaModel;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.PacienteModel;

public class AdapterPaciente extends RecyclerView.Adapter<AdapterPaciente.MyViewHolder> {

    private DatabaseReference refRelatorio = FirebaseDatabase.getInstance().getReference().child("relatoriodiario");
    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomePaciente.setText(pacientes.get(position).getNome());

        holder.itemView.getRootView().setClickable(false);
        holder.maisOpcoes.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(c, view);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            // Definir um listener de clique para tratar as seleções do menu
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                if (menuItem.getItemId() == R.id.relatorio) {

                    AlertDialog.Builder b = new AlertDialog.Builder(c);
                    b.setCancelable(false);
                    b.setMessage("Aguarde...");
                    Dialog dialogCarregando = b.create();
                    dialogCarregando.show();

                    refRelatorio.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.exists()) {
                                List<AvaDiariaModel> avaliacoes = new ArrayList<>();
                                for (DataSnapshot dado : snapshot.getChildren()) {
                                    AvaDiariaModel AvaDiariaModel = dado.getValue(AvaDiariaModel.class);
                                    if (AvaDiariaModel.getIdPaciente().equals(pacientes.get(position).getId())) {
                                        avaliacoes.add(AvaDiariaModel);
                                    }
                                }

                                Collections.reverse(avaliacoes);
                                if (!avaliacoes.isEmpty()) {

                                    refDiagnosticos.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {

                                                List<DiagnosticoPacienteModel> diagnosticos = new ArrayList<>();
                                                for (DataSnapshot dado : snapshot.getChildren()) {
                                                    DiagnosticoPacienteModel diagnosticoPacienteModel = dado.getValue(DiagnosticoPacienteModel.class);
                                                    if (diagnosticoPacienteModel.getIdPaciente().equals(pacientes.get(position).getId())) {
                                                        diagnosticos.add(diagnosticoPacienteModel);
                                                    }
                                                }

                                                Collections.reverse(diagnosticos);


                                                Toast.makeText(c, "Gerando o PDF", Toast.LENGTH_SHORT).show();
                                                new Thread(
                                                        () -> {
                                                            generateAndSharePDF(c, avaliacoes.get(0), pacientes.get(position), diagnosticos, dialogCarregando);
                                                        }
                                                ).start();

                                            } else {
                                                Toast.makeText(c, "Dados Vazios no Servidor", Toast.LENGTH_SHORT).show();
                                                dialogCarregando.dismiss();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(c, "Dados Vazios no Servidor", Toast.LENGTH_SHORT).show();
                                            dialogCarregando.dismiss();
                                        }
                                    });

                                } else {
                                    Toast.makeText(c, "Dados Vazios no Servidor", Toast.LENGTH_SHORT).show();
                                    dialogCarregando.dismiss();
                                }
                            } else {
                                Toast.makeText(c, "Dados Vazios no Servidor", Toast.LENGTH_SHORT).show();
                                dialogCarregando.dismiss();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(c, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (menuItem.getItemId() == R.id.avaliacao) {
                    Intent i = new Intent(c, RelatorioDiarioActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", pacientes.get(position).getId());
                    c.startActivity(i);
                } else if (menuItem.getItemId() == R.id.atualizar) {
                    Intent i = new Intent(c, NovoPacienteActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("update", true);
                    i.putExtra("id", pacientes.get(position).getId());
                    c.startActivity(i);
                } else if (menuItem.getItemId() == R.id.diagnostico) {
                    Intent i = new Intent(c, DiagnosticoEnfermagemActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", pacientes.get(position).getId());
                    c.startActivity(i);
                } else if (menuItem.getItemId() == R.id.intervencao) {
                    Intent i = new Intent(c, IntervencoesActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("id", pacientes.get(position).getId());
                    c.startActivity(i);
                }

                return false;
            });

            // Exibir o menu suspenso
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomePaciente;
        ImageView maisOpcoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nomePaciente = itemView.findViewById(R.id.nome_paciente);
            maisOpcoes = itemView.findViewById(R.id.mais_opces);
        }
    }
}
