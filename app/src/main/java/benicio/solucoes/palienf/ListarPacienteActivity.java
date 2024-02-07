package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import benicio.solucoes.palienf.adapter.AdapterPaciente;
import benicio.solucoes.palienf.databinding.ActivityListarPacienteBinding;
import benicio.solucoes.palienf.databinding.ActivityNovoPacienteBinding;
import benicio.solucoes.palienf.model.PacienteModel;

public class ListarPacienteActivity extends AppCompatActivity {

    private ActivityListarPacienteBinding mainBinding;
    private DatabaseReference refPacientes = FirebaseDatabase.getInstance().getReference().child("pacientes");
    private AdapterPaciente adapterPaciente;
    public List<PacienteModel> pacientes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityListarPacienteBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Pacientes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        configurarRecycler();
        configurarListener("");
    }

    @Override
    protected void onStart() {
        configurarListener("");
        super.onStart();
    }

    private void configurarRecycler() {
        mainBinding.recyclerPacientes.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.recyclerPacientes.setHasFixedSize(true);
        mainBinding.recyclerPacientes.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapterPaciente = new AdapterPaciente(pacientes, this);
        mainBinding.recyclerPacientes.setAdapter(adapterPaciente);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }

    private void configurarListener(String query){
        refPacientes.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if ( snapshot.exists() ){
                    pacientes.clear();
                    for ( DataSnapshot dado : snapshot.getChildren()){
                        PacienteModel pacienteModel = dado.getValue(PacienteModel.class);
                        if ( query.isEmpty() ){
                            pacientes.add(pacienteModel);
                        }else{
                            assert pacienteModel != null;
                            if (
                                    pacienteModel.getNome().toLowerCase().trim().contains(query)
                                         
                            ){
                                pacientes.add(pacienteModel);
                            }
                        }
                    }

                    adapterPaciente.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesquisa, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                configurarListener(newText.toLowerCase().trim());
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}