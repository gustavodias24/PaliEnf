package benicio.solucoes.palienf.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.databinding.LayoutDiagnosticoBinding;
import benicio.solucoes.palienf.databinding.LayoutNocBinding;
import benicio.solucoes.palienf.databinding.LayoutVerCaracteristicasBinding;
import benicio.solucoes.palienf.model.DiagnosticoModel;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.IntervencaoModel;
import benicio.solucoes.palienf.model.NocModel;

public class AdapterDiagnostico extends RecyclerView.Adapter<AdapterDiagnostico.MyViewHolder> {
    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
    List<DiagnosticoModel> diagnosticos;
    Activity c;
    Dialog dialogNoc;
    Dialog dialogIntervencao;
    String idPaciente;

    public AdapterDiagnostico(List<DiagnosticoModel> diagnosticos, Activity c, String idPaciente) {
        this.diagnosticos = diagnosticos;
        this.c = c;
        this.idPaciente = idPaciente;
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

        holder.verCaracteristicas.setOnClickListener(view -> {
            AlertDialog.Builder b = new AlertDialog.Builder(c);
            b.setTitle("Características");
            LayoutVerCaracteristicasBinding caracteristicasBinding = LayoutVerCaracteristicasBinding.inflate(c.getLayoutInflater());
            Picasso.get().load(d.getTabelaNoc()).into(caracteristicasBinding.imageView);
            b.setPositiveButton("Fechar", null);
            b.setView(caracteristicasBinding.getRoot());
            b.create().show();

        });


//        holder.titulo_diagnostico_text.setText( "noc_"+(d.getPosition()+1) + " "+ d.getNome());
        holder.titulo_diagnostico_text.setText(d.getNome());
        holder.descricao_diagnostico_text.setText(d.getDescricao());

        holder.botao_noc.setOnClickListener(view -> {

            AlertDialog.Builder b = new AlertDialog.Builder(c);
            LayoutNocBinding diagnosticoBinding = LayoutNocBinding.inflate(c.getLayoutInflater());

            for (NocModel noc : d.getNocs()) {
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
                DiagnosticoPacienteModel diagnosticoPaciente = new DiagnosticoPacienteModel();
                diagnosticoPaciente.setNocs(d.getNocs());
                diagnosticoPaciente.setIntervencoes(d.getIntervencoes());

                for (int i = 0; i < diagnosticoBinding.layoutNocs.getChildCount(); i++) {
                    View child = diagnosticoBinding.layoutNocs.getChildAt(i);
                    // Verificar se é um CheckBox e se está marcado
                    if (child instanceof RadioGroup) {
                        RadioGroup radioGroup = (RadioGroup) child;
                        boolean marcado = false;
                        StringBuilder nocPaciente = new StringBuilder();
                        for (int i2 = 0; i2 < radioGroup.getChildCount(); i2++) {
                            View child2 = radioGroup.getChildAt(i2);
                            if (child2 instanceof RadioButton) {
                                RadioButton radioButton = (RadioButton) child2;
                                if (radioButton.isChecked()) {
                                    marcado = true;
//                                    nocPaciente.append("R- ").append(radioButton.getText().toString()).append("\n");
                                    nocPaciente.append(radioButton.getText().toString());
                                    diagnosticoPaciente.getNocSelecionadas().add(nocPaciente.toString());
                                    nocPaciente = new StringBuilder();
                                    break;
                                }
                            } else if (child2 instanceof TextView) {
                                TextView textView = (TextView) child2;
                                nocPaciente.append(textView.getText().toString()).append("\n");
                            }
                        }

                        if (!marcado) {
//                            nocPaciente.append("R- ").append("Não Marcado\n");
                            diagnosticoPaciente.getNocSelecionadas().add(nocPaciente.toString());
                            nocPaciente = new StringBuilder();
                        }
                    }
                }

                AlertDialog.Builder b2 = new AlertDialog.Builder(c);
                LayoutNocBinding diagnosticoBinding2 = LayoutNocBinding.inflate(c.getLayoutInflater());

                diagnosticoBinding2.textView10.setText("Intervenções");
                for (IntervencaoModel intervencao : d.getIntervencoes()) {
                    CheckBox checkBox = new CheckBox(c);
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    checkBox.setText(intervencao.getDescricao());
                    diagnosticoBinding2.layoutIntervencoes.addView(checkBox);
                }

                EditText campoLivre = new EditText(c);
                campoLivre.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                campoLivre.setGravity(Gravity.START | Gravity.TOP);
                campoLivre.setHint("Observações");
                campoLivre.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                diagnosticoBinding2.layoutIntervencoes.addView(campoLivre);

                Button buttonConcluir = new Button(c);
                buttonConcluir.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonConcluir.setBackgroundResource(R.color.azul);
                buttonConcluir.setText("Concluir");
                buttonConcluir.setAllCaps(true);
                buttonConcluir.setTextColor(Color.WHITE);
                buttonConcluir.setTypeface(null, Typeface.BOLD);
                diagnosticoBinding2.layoutNocs.addView(buttonConcluir);

                buttonConcluir.setOnClickListener(view1 -> {

                    diagnosticoPaciente.getIntervencoes().clear();


                    for (int i = 0; i < diagnosticoBinding2.layoutIntervencoes.getChildCount(); i++) {
                        View child = diagnosticoBinding2.layoutIntervencoes.getChildAt(i);

                        IntervencaoModel intervencaoModel = new IntervencaoModel();
                        if (child instanceof CheckBox) {
                            CheckBox checkBox = (CheckBox) child;

                            intervencaoModel.setDescricao(checkBox.getText().toString());

                            if (checkBox.isChecked()) {
                                intervencaoModel.setSelecionado(true);
                            }
                            diagnosticoPaciente.getIntervencoes().add(intervencaoModel);

                        }
                    }


                    String intervencaoNova = campoLivre.getText().toString();

                    if (!intervencaoNova.isEmpty()) {
                        IntervencaoModel intervencaoModel = new IntervencaoModel();
                        intervencaoModel.setDescricao(intervencaoNova);
                        intervencaoModel.setSelecionado(true);
                        diagnosticoPaciente.getIntervencoes().add(intervencaoModel);
                    }

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dataAtual = dateFormat.format(new Date());

                    diagnosticoPaciente.setDataCriacao(dataAtual);
                    diagnosticoPaciente.setId(UUID.randomUUID().toString());
                    diagnosticoPaciente.setIdPaciente(idPaciente);

                    diagnosticoPaciente.setTitulo(d.getNome());
                    diagnosticoPaciente.setSubTitulo(d.getDescricao());

                    refDiagnosticos.child(diagnosticoPaciente.getId()).setValue(diagnosticoPaciente).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(c, "Diagnóstico Criado!", Toast.LENGTH_SHORT).show();
                            dialogIntervencao.dismiss();
                        }
                    });


                });


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
        return diagnosticos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo_diagnostico_text, descricao_diagnostico_text;
        Button botao_noc, verCaracteristicas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo_diagnostico_text = itemView.findViewById(R.id.titulo_diagnostico_text);
            descricao_diagnostico_text = itemView.findViewById(R.id.descricao_diagnostico_text);
            botao_noc = itemView.findViewById(R.id.botao_noc);
            verCaracteristicas = itemView.findViewById(R.id.verCaracteristicas);

        }
    }
}
