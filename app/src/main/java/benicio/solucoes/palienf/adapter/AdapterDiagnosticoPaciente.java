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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.databinding.LayoutNocBinding;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.IntervencaoModel;
import benicio.solucoes.palienf.model.NocModel;

public class AdapterDiagnosticoPaciente extends RecyclerView.Adapter<AdapterDiagnosticoPaciente.MyViewHolder> implements View.OnClickListener {

    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
    List<DiagnosticoPacienteModel> lista;
    Activity c;
    Dialog dialogNoc, dialogEdicao;
    Dialog dialogIntervencao;

    private RadioButton ultimoRadioButton = null;

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

            diagnosticoBinding.textView10.setVisibility(View.GONE);

            TextView tituloNoc = new TextView(c);
            tituloNoc.setText("Noc's selecionadas");
            tituloNoc.setTextColor(c.getResources().getColor(R.color.azul));
            tituloNoc.setTextSize(20);
            tituloNoc.setTypeface(null, Typeface.BOLD);

            diagnosticoBinding.layoutNocs.addView(tituloNoc);

            for (String noc : diagnosticoPacienteModel.getNocSelecionadas()) {
                TextView nocSelecionada = new TextView(c);
                nocSelecionada.setText(noc);
                nocSelecionada.setTextColor(c.getResources().getColor(R.color.black));
                nocSelecionada.setTextSize(14);
                nocSelecionada.setTypeface(null, Typeface.ITALIC);
                diagnosticoBinding.layoutNocs.addView(nocSelecionada);
            }

            TextView tituloIntervencao = new TextView(c);
            tituloIntervencao.setText("Intervenções selecionadas");
            tituloIntervencao.setTextColor(c.getResources().getColor(R.color.azul));
            tituloIntervencao.setTextSize(20);
            tituloIntervencao.setTypeface(null, Typeface.BOLD);

            diagnosticoBinding.layoutNocs.addView(tituloIntervencao);

            for (IntervencaoModel intervencaoModel : diagnosticoPacienteModel.getIntervencoes()) {
                if (intervencaoModel.isSelecionado()) {
                    TextView IntervencaoSelecionada = new TextView(c);
                    IntervencaoSelecionada.setText(intervencaoModel.getDescricao());
                    IntervencaoSelecionada.setTextColor(c.getResources().getColor(R.color.black));
                    IntervencaoSelecionada.setTextSize(14);
                    IntervencaoSelecionada.setTypeface(null, Typeface.ITALIC);
                    diagnosticoBinding.layoutNocs.addView(IntervencaoSelecionada);
                }
            }

            Button buttonFechar = new Button(c);
            buttonFechar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonFechar.setBackgroundResource(R.color.azul);
            buttonFechar.setText("Fechar");
            buttonFechar.setAllCaps(true);
            buttonFechar.setTextColor(Color.WHITE);
            buttonFechar.setTypeface(null, Typeface.BOLD);

            TextView penis = new TextView(c);
            penis.setText(" ");
            penis.setTextColor(c.getResources().getColor(R.color.azul));
            penis.setTextSize(20);
            penis.setTypeface(null, Typeface.BOLD);

            TextView penis2 = new TextView(c);
            penis2.setText(" ");
            penis2.setTextColor(c.getResources().getColor(R.color.azul));
            penis2.setTextSize(10);
            penis2.setTypeface(null, Typeface.BOLD);


            Button buttonEditar = new Button(c);
            buttonEditar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            buttonEditar.setBackgroundResource(R.color.azul);
            buttonEditar.setText("Editar");
            buttonEditar.setAllCaps(true);
            buttonEditar.setTextColor(Color.WHITE);
            buttonEditar.setTypeface(null, Typeface.BOLD);

            buttonEditar.setOnClickListener(viewEditar -> {
                dialogNoc.dismiss();

                AlertDialog.Builder bEdticao = new AlertDialog.Builder(c);
                LayoutNocBinding diagnosticoBindingEdicao = LayoutNocBinding.inflate(c.getLayoutInflater());

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
                        radioButton.setOnClickListener(this);
                        radioButton.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        radioButton.setText(indicador);
                        radioGroup.addView(radioButton);
                    }
                    diagnosticoBindingEdicao.layoutNocs.addView(radioGroup);

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

                diagnosticoBindingEdicao.layoutNocs.addView(button);

                button.setOnClickListener(itenversoesView -> {
                    DiagnosticoPacienteModel diagnosticoPaciente = new DiagnosticoPacienteModel();
                    diagnosticoPaciente.setNocs(diagnosticoPacienteModel.getNocs());
                    diagnosticoPaciente.setIntervencoes(diagnosticoPacienteModel.getIntervencoes());

                    for (int i = 0; i < diagnosticoBindingEdicao.layoutNocs.getChildCount(); i++) {
                        View child = diagnosticoBindingEdicao.layoutNocs.getChildAt(i);
                        // Verificar se é um CheckBox e se está marcado
                        if (child instanceof RadioGroup) {
                            RadioGroup radioGroup = (RadioGroup) child;
                            StringBuilder nocPaciente = new StringBuilder();
                            for (int i2 = 0; i2 < radioGroup.getChildCount(); i2++) {
                                View child2 = radioGroup.getChildAt(i2);
                                if (child2 instanceof RadioButton) {
                                    RadioButton radioButton = (RadioButton) child2;
                                    if (radioButton.isChecked()) {
                                        nocPaciente.append(radioButton.getText().toString());
                                        diagnosticoPaciente.getNocSelecionadas().add(nocPaciente.toString());
                                        Log.d("mayara", "onBindViewHolder: " + nocPaciente.toString());
                                        break;
                                    }
                                } else if (child2 instanceof TextView) {
                                    TextView textView = (TextView) child2;
                                    nocPaciente.append(textView.getText().toString()).append("? ");
                                }
                            }
                        }
                    }

                    AlertDialog.Builder b2 = new AlertDialog.Builder(c);
                    LayoutNocBinding diagnosticoBinding2 = LayoutNocBinding.inflate(c.getLayoutInflater());

                    diagnosticoBinding2.textView10.setText("Intervenções");
                    for (IntervencaoModel intervencao : diagnosticoPacienteModel.getIntervencoes()) {
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
                        diagnosticoPaciente.setId(diagnosticoPacienteModel.getId());
                        diagnosticoPaciente.setIdPaciente(diagnosticoPacienteModel.getIdPaciente());

                        diagnosticoPaciente.setTitulo(diagnosticoPacienteModel.getTitulo());
                        diagnosticoPaciente.setSubTitulo(diagnosticoPacienteModel.getSubTitulo());

                        refDiagnosticos.child(diagnosticoPaciente.getId()).setValue(diagnosticoPaciente).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(c, "Diagnóstico Editado!", Toast.LENGTH_SHORT).show();
                                dialogIntervencao.dismiss();
                            }
                        });


                    });


                    b2.setView(diagnosticoBinding2.getRoot());
                    dialogEdicao.dismiss();
                    dialogIntervencao = b2.create();
                    dialogIntervencao.show();
                });

                bEdticao.setView(diagnosticoBindingEdicao.getRoot());
                dialogEdicao = bEdticao.create();
                dialogEdicao.show();

                //to do
            });

            diagnosticoBinding.layoutNocs.addView(penis);
            diagnosticoBinding.layoutNocs.addView(buttonFechar);
            diagnosticoBinding.layoutNocs.addView(penis2);
            diagnosticoBinding.layoutNocs.addView(buttonEditar);

            buttonFechar.setOnClickListener(viewfechar -> dialogNoc.dismiss());


            b.setView(diagnosticoBinding.getRoot());
            dialogNoc = b.create();
            dialogNoc.show();
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if (v instanceof RadioButton) {
            RadioButton radioButton = (RadioButton) v;

            if (ultimoRadioButton == radioButton) {
                // Se o mesmo RadioButton for clicado duas vezes seguidas, desmarque-o
                radioButton.setChecked(false);
                ultimoRadioButton = null;
            } else {
                // Se for clicado pela primeira vez, marque-o e atualize o último RadioButton clicado
                radioButton.setChecked(true);
                ultimoRadioButton = radioButton;
            }
        }
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
