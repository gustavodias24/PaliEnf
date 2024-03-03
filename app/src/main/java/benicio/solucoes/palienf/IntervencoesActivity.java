package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.ArrayList;
import java.util.List;

import benicio.solucoes.palienf.databinding.ActivityIntervencoesBinding;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.IntervencaoModel;

public class IntervencoesActivity extends AppCompatActivity {

    private List<String> idDiagnosticos = new ArrayList<>();
    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
    private ActivityIntervencoesBinding mainbBinding;
    private Bundle b;
    boolean semNada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainbBinding = ActivityIntervencoesBinding.inflate(getLayoutInflater());
        setContentView(mainbBinding.getRoot());

        getSupportActionBar().setTitle("Intervenções");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        b = getIntent().getExtras();

        semNada = true;

        refDiagnosticos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dado : snapshot.getChildren()) {
                        DiagnosticoPacienteModel diagnostico = dado.getValue(DiagnosticoPacienteModel.class);

                        if (diagnostico.isAtivo() && diagnostico.getIdPaciente().equals(b.getString("id", ""))) {
                            idDiagnosticos.add(diagnostico.getId());
                            for (IntervencaoModel intervencao : diagnostico.getIntervencoes()) {


                                for (String intervencaoSelecionado : diagnostico.getIntervensoeSelecionadas()) {
                                    if (intervencaoSelecionado.equals(intervencao.getDescricao())) {
                                        CheckBox checkBox = new CheckBox(IntervencoesActivity.this);
                                        checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.WRAP_CONTENT));
                                        checkBox.setChecked(true);
                                        checkBox.setText(intervencao.getDescricao());
                                        mainbBinding.layout.addView(checkBox);

                                        EditText editText = new EditText(IntervencoesActivity.this);
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT);
                                        editText.setLayoutParams(params);
                                        editText.setHint("Data/Hora da resolução da intervenção");
                                        editText.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_CLASS_NUMBER);
                                        mainbBinding.layout.addView(editText);

                                        // Criar Button
                                        Button button = new Button(IntervencoesActivity.this);
                                        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                                                ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT);
                                        button.setLayoutParams(params2);
                                        button.setText("Resolvido");
                                        button.setTextColor(getResources().getColor(android.R.color.white));
                                        button.setBackgroundColor(getResources().getColor(R.color.azul));
                                        button.setAllCaps(false);
                                        button.setTextSize(16); // Defina o tamanho da fonte conforme necessário
                                        button.setPadding(20, 20, 20, 20); // Adicione o preenchimento conforme necessário
                                        button.setTypeface(null, Typeface.BOLD); // Defina o estilo da fonte como negrito
                                        mainbBinding.layout.addView(button);

                                        break;
                                    }
                                }
                                semNada = false;
                            }
                        }
                    }

                    if (semNada) {
                        mainbBinding.layout2.setVisibility(View.GONE);
                        mainbBinding.info.setText("Sem Nenhuma Intervenção para ser exibida!");
                    } else {
                        mainbBinding.info.setText("Informações das Intervenções");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        mainbBinding.botaoResolvido.setOnClickListener(view -> {
//            String dataHoraResolucao = mainbBinding.dataHoraResolvido.getText().toString();
//
//            if (!dataHoraResolucao.isEmpty()) {
//                for (String id : idDiagnosticos) {
//                    refDiagnosticos.child(id).addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                            if (snapshot.exists()) {
//                                DiagnosticoPacienteModel diagnosticoPacienteModel = snapshot.getValue(DiagnosticoPacienteModel.class);
//                                assert diagnosticoPacienteModel != null;
//                                diagnosticoPacienteModel.setDataHoraResolucaoIntervencao(dataHoraResolucao);
//                                diagnosticoPacienteModel.setAtivo(false);
//                                refDiagnosticos.child(id).setValue(diagnosticoPacienteModel);
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
//
//                Toast.makeText(this, "Resolução das Intervenções Concluídas!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Preencha as Informações de Data/hora", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}