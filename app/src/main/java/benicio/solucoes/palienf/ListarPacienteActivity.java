package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    private static final int REQUEST_STORAGE_PERMISSION = 1001;
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

        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(this, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, REQUEST_STORAGE_PERMISSION );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //do here
                } else {
                    Toast.makeText(this, "Usuário Negou Acesso!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    protected void onStart() {
        configurarListener("");
        super.onStart();
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
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
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configurarListener(String query) {
        refPacientes.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    pacientes.clear();
                    for (DataSnapshot dado : snapshot.getChildren()) {
                        PacienteModel pacienteModel = dado.getValue(PacienteModel.class);
                        if (query.isEmpty()) {
                            pacientes.add(pacienteModel);
                        } else {
                            assert pacienteModel != null;
                            if (
                                    pacienteModel.getNome().toLowerCase().trim().contains(query)

                            ) {
                                pacientes.add(pacienteModel);
                            }
                        }
                    }
                    mainBinding.carregandoText.setVisibility(View.GONE);
                    adapterPaciente.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mainBinding.carregandoText.setText(error.getMessage());
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