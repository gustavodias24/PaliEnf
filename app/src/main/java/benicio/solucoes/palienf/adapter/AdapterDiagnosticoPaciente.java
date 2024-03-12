package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.databinding.LayoutNocBinding;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.IntervencaoModel;
import benicio.solucoes.palienf.model.NocModel;

public class AdapterDiagnosticoPaciente extends RecyclerView.Adapter<AdapterDiagnosticoPaciente.MyViewHolder> {

    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
    List<DiagnosticoPacienteModel> lista;
    Activity c;
    Dialog dialogNoc;
    Dialog dialogIntervencao;

    public AdapterDiagnosticoPaciente(List<DiagnosticoPacienteModel> lista, Activity c) {
        this.lista = lista;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_exibicao, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DiagnosticoPacienteModel diagnosticoPacienteModel = lista.get(position);

        holder.itemView.getRootView().setClickable(false);

        holder.titulo.setText(diagnosticoPacienteModel.getTitulo());
        holder.subTitulo.setText(diagnosticoPacienteModel.getSubTitulo());

        holder.btnResolvido.setOnClickListener(view -> {
            diagnosticoPacienteModel.setAtivo(false);
            refDiagnosticos.child(diagnosticoPacienteModel.getId()).setValue(diagnosticoPacienteModel).addOnCompleteListener(task -> {
                Toast.makeText(c, "Resolvido!", Toast.LENGTH_SHORT).show();
            });
        });

        holder.btnNoc.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(c);
            LayoutNocBinding diagnosticoBinding = LayoutNocBinding.inflate(c.getLayoutInflater());

            for (NocModel noc : diagnosticoPacienteModel.getNocs()) {
                RadioGroup radioGroup = new RadioGroup(c);
                radioGroup.setLayoutParams(new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.MATCH_PARENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT));
                radioGroup.setOrientation(RadioGroup.VERTICAL);

                TextView textView = new TextView(c);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(noc.getNome());
                radioGroup.addView(textView);

                for (String indicador : noc.getPossiveisIndicadores()) {
                    RadioButton radioButton = new RadioButton(c);
                    radioButton.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    for (String nocSelecionada : diagnosticoPacienteModel.getNocSelecionadas()) {
                        try {
                            String nomeNoc = nocSelecionada.split("\n")[0];
                            String tituloNoc = nocSelecionada.split("\n")[1];

                            if (nomeNoc.trim().equals(noc.getNome().trim()) && tituloNoc.trim().equals(indicador.trim())) {
                                radioButton.setChecked(true);
                                break;
                            }
                        } catch (Exception ignored) {
                        }
                    }
                    radioButton.setText(indicador);
                    radioGroup.addView(radioButton);
                }
                diagnosticoBinding.layoutNocs.addView(radioGroup);
            }

            Button button = new Button(c);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setBackgroundResource(R.color.azul);
            button.setText("Próximo");
            button.setAllCaps(true);
            button.setTextColor(Color.WHITE);
            button.setTypeface(null, Typeface.BOLD);

            diagnosticoBinding.layoutNocs.addView(button);

            button.setOnClickListener(itenversoesView -> {
                AlertDialog.Builder b2 = new AlertDialog.Builder(c);
                LayoutNocBinding diagnosticoBinding2 = LayoutNocBinding.inflate(c.getLayoutInflater());

                diagnosticoBinding2.textView10.setText("Intervenções");
                for (IntervencaoModel intervencao : diagnosticoPacienteModel.getIntervencoes()) {
                    CheckBox checkBox = new CheckBox(c);
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    if (intervencao.isSelecionado()) {
                        checkBox.setChecked(true);
                    }

                    checkBox.setText(intervencao.getDescricao());
                    diagnosticoBinding2.layoutNocs.addView(checkBox);
                }

                Button buttonConcluir = new Button(c);
                buttonConcluir.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonConcluir.setBackgroundResource(R.color.azul);
                buttonConcluir.setText("OK");
                buttonConcluir.setAllCaps(true);
                buttonConcluir.setTextColor(Color.WHITE);
                buttonConcluir.setTypeface(null, Typeface.BOLD);
                diagnosticoBinding2.layoutNocs.addView(buttonConcluir);

                buttonConcluir.setOnClickListener(view1 -> dialogIntervencao.dismiss());

                b2.setView(diagnosticoBinding2.getRoot());
                dialogNoc.dismiss();
                dialogIntervencao = b2.create();
                dialogIntervencao.show();
            });

            b.setView(diagnosticoBinding.getRoot());
            dialogNoc = b.create();
            dialogNoc.show();
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo, subTitulo;
        Button btnResolvido, btnNoc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnResolvido = itemView.findViewById(R.id.botao_resolvido);
            btnNoc = itemView.findViewById(R.id.botao_noc_exibicao);
            titulo = itemView.findViewById(R.id.titulo_diagnostico_text_exibicao);
            subTitulo = itemView.findViewById(R.id.sub_titulo_diagnostico_text_exibicao);
        }
    }
}
