package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import benicio.solucoes.palienf.adapter.AdapterDiagnostico;
import benicio.solucoes.palienf.adapter.AdapterDiagnosticoPaciente;
import benicio.solucoes.palienf.databinding.ActivityDiagnosticoEnfermagemBinding;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;

public class DiagnosticoEnfermagemActivity extends AppCompatActivity {

    private DatabaseReference refDiagnosticos = FirebaseDatabase.getInstance().getReference().child("diagnostico");
    private ActivityDiagnosticoEnfermagemBinding mainBinding;
    private RecyclerView recyclerDiagnosticos;
    private AdapterDiagnosticoPaciente adapterDiagnostico;
    private List<DiagnosticoPacienteModel> listaSelecionadaStringDiagnosticos = new ArrayList<>();
    String idPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityDiagnosticoEnfermagemBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Diagnóstico de enfermagem ativos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            idPaciente = b.getString("id", "");
            Intent i = new Intent(this, CriarDiagnosticoActivity.class);
            i.putExtra("id", idPaciente);
            mainBinding.novoDiagnostico.setOnClickListener(view -> startActivity(i));
        }

        mainBinding.button.setOnClickListener(view -> {
            if (recyclerDiagnosticos.getVisibility() == View.GONE) {
                recyclerDiagnosticos.setVisibility(View.VISIBLE);
            } else {
                recyclerDiagnosticos.setVisibility(View.GONE);
            }
        });
        configurarRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configurarRecyclerView() {
        recyclerDiagnosticos = mainBinding.recyclerDiagnosticos;
        recyclerDiagnosticos.setLayoutManager(new LinearLayoutManager(this));
        recyclerDiagnosticos.setHasFixedSize(true);
        recyclerDiagnosticos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapterDiagnostico = new AdapterDiagnosticoPaciente(listaSelecionadaStringDiagnosticos, this);
        recyclerDiagnosticos.setAdapter(adapterDiagnostico);

        refDiagnosticos.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    listaSelecionadaStringDiagnosticos.clear();
                    for (DataSnapshot dado : snapshot.getChildren()) {
                        DiagnosticoPacienteModel diagnosticoPacienteModel = dado.getValue(DiagnosticoPacienteModel.class);
                        assert diagnosticoPacienteModel != null;
                        if (diagnosticoPacienteModel.getIdPaciente().equals(idPaciente) && diagnosticoPacienteModel.isAtivo()) {
                            listaSelecionadaStringDiagnosticos.add(diagnosticoPacienteModel);
                        }
                    }
                    adapterDiagnostico.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}