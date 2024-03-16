package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.solucoes.palienf.databinding.ActivityNovoPacienteBinding;
import benicio.solucoes.palienf.databinding.ActivityRelatarProblemaBinding;
import benicio.solucoes.palienf.model.PacienteModel;

public class NovoPacienteActivity extends AppCompatActivity {

    private ActivityNovoPacienteBinding mainBinding;
    private DatabaseReference refPacientes = FirebaseDatabase.getInstance().getReference().child("pacientes");
    private Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityNovoPacienteBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        b = getIntent().getExtras();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Cadastrar novo paciente");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if ( b != null){
            if ( b.getBoolean("update", false)){
                refPacientes.child(b.getString("id", "")).get().addOnCompleteListener(  task -> {
                    PacienteModel pacienteModel =  task.getResult().getValue(PacienteModel.class);
                    if ( pacienteModel != null ){

                        mainBinding.sexo.setText(pacienteModel.getSexo());
                        mainBinding.nome.setText(pacienteModel.getNome());
                        mainBinding.dataNascimento.setText(pacienteModel.getDataNascimento());
                        mainBinding.cpf.setText(pacienteModel.getCpf());
                        mainBinding.prontuario.setText(pacienteModel.getProntuário());

                        mainBinding.cidade.setText(pacienteModel.getCidade());
                        mainBinding.bairro.setText(pacienteModel.getBairro());
                        mainBinding.cep.setText(pacienteModel.getCep());
                        mainBinding.numero.setText(pacienteModel.getNumero());
                        mainBinding.referencia.setText(pacienteModel.getPontoReferencia());


                        mainBinding.financeiro.setChecked(pacienteModel.isNecessidadeFinanceira());
                        mainBinding.descriFinanceiro.setText(pacienteModel.getDescriNecessidadeFinanceira());

                        mainBinding.familiaPresente.setChecked(pacienteModel.isNecessidadeMenbroFamilia());
                        mainBinding.descriFamiliaPresente.setText(pacienteModel.getDescriNecessidadeMenbroFamilia());

                        mainBinding.crencaReligiao.setChecked(pacienteModel.isCrencaReligiao());
                        mainBinding.descriCrencaReligiao.setText(pacienteModel.getDescriCrencaReligiao());

                        mainBinding.visitarLider.setChecked(pacienteModel.isCrencaVistirarLider());

                        mainBinding.ritoEspiritual.setChecked(pacienteModel.isCrencaRitoEspiritual());
                        mainBinding.descriRituEspiritual.setText(pacienteModel.getDescriRitoEspiritual());

                        mainBinding.alergias.setText(pacienteModel.getAlergias());
                        mainBinding.internacaoRecente.setText(pacienteModel.getInternacaoRecente());
                        mainBinding.doencaOncologica.setText(pacienteModel.getDoencaOncológica());

                        mainBinding.procedencia.setText(pacienteModel.getProcedencia());
                        mainBinding.motivo.setText(pacienteModel.getMotidaInternacao());
                        mainBinding.acampanhante.setText(pacienteModel.getNomeCompanhante());
                        mainBinding.peso.setText(pacienteModel.getPeso());
                        mainBinding.altura.setText(pacienteModel.getAltura());
                        mainBinding.sc.setText(pacienteModel.getSc());
                        mainBinding.imc.setText(pacienteModel.getImc());
                        mainBinding.numeroLeito.setText(pacienteModel.getNumeroLeito());

                    }
                });


                getSupportActionBar().setTitle("Atualizar paciente");
                mainBinding.cadastrar.setText("Atualizar");
            }
        }

        mainBinding.cadastrar.setOnClickListener( view -> {

            if (
                    mainBinding.nome.getText().toString().isEmpty() ||
                    mainBinding.dataNascimento.getText().toString().isEmpty() ||
                    mainBinding.prontuario.getText().toString().isEmpty()
            ){
                Toast.makeText(this, "Preencha todas as informações obrigatórias!", Toast.LENGTH_SHORT).show();
            }else{
                PacienteModel paciente = new PacienteModel();
                if ( b == null ){
                    paciente.setId(UUID.randomUUID().toString());
                }else{
                    paciente.setId(b.getString("id", ""));
                }
                paciente.setSexo(mainBinding.sexo.getText().toString());
                paciente.setNome(mainBinding.nome.getText().toString());
                paciente.setDataNascimento(mainBinding.dataNascimento.getText().toString());
                paciente.setCpf(mainBinding.cpf.getText().toString());
                paciente.setProntuário(mainBinding.prontuario.getText().toString());
                paciente.setCidade(mainBinding.cidade.getText().toString());
                paciente.setBairro(mainBinding.bairro.getText().toString());
                paciente.setCep(mainBinding.cep.getText().toString());
                paciente.setNumero(mainBinding.numero.getText().toString());
                paciente.setPontoReferencia(mainBinding.referencia.getText().toString());

                paciente.setNecessidadeFinanceira(mainBinding.financeiro.isChecked());
                paciente.setDescriNecessidadeFinanceira(mainBinding.descriFinanceiro.getText().toString());

                paciente.setCrencaReligiao(mainBinding.crencaReligiao.isChecked());
                paciente.setDescriCrencaReligiao(mainBinding.descriCrencaReligiao.getText().toString());


                paciente.setNecessidadeMenbroFamilia(mainBinding.familiaPresente.isChecked());
                paciente.setDescriNecessidadeMenbroFamilia(mainBinding.descriFamiliaPresente.getText().toString());


                paciente.setCrencaVistirarLider(mainBinding.visitarLider.isChecked());


                paciente.setCrencaRitoEspiritual(mainBinding.ritoEspiritual.isChecked());
                paciente.setDescriRitoEspiritual(mainBinding.descriRituEspiritual.getText().toString());

                paciente.setAlergias(mainBinding.alergias.getText().toString());
                paciente.setInternacaoRecente(mainBinding.internacaoRecente.getText().toString());
                paciente.setDoencaOncológica(mainBinding.doencaOncologica.getText().toString());
                paciente.setProcedencia(mainBinding.procedencia.getText().toString());
                paciente.setMotidaInternacao(mainBinding.motivo.getText().toString());
                paciente.setNomeCompanhante(mainBinding.acampanhante.getText().toString());
                paciente.setPeso(mainBinding.peso.getText().toString());
                paciente.setAltura(mainBinding.altura.getText().toString());
                paciente.setSc(mainBinding.sc.getText().toString());
                paciente.setImc(mainBinding.imc.getText().toString());
                paciente.setNumeroLeito(mainBinding.numeroLeito.getText().toString());

                refPacientes.child(paciente.getId()).setValue(paciente).addOnCompleteListener( task ->{
                    if ( task.isSuccessful() ){
                        if ( b == null){
                            Toast.makeText(this, "Paciente criado com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(this, this.getClass()));
                        }else{
                            Toast.makeText(this, "Paciente atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }
}