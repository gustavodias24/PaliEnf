package benicio.solucoes.palienf;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import benicio.solucoes.palienf.adapter.AdapterEnfermeiro;
import benicio.solucoes.palienf.databinding.ActivityMenuBinding;
import benicio.solucoes.palienf.databinding.ActivityVerEnfermeirosBinding;
import benicio.solucoes.palienf.model.UsuarioModel;

public class VerEnfermeirosActivity extends AppCompatActivity {

    private ActivityVerEnfermeirosBinding mainBinding;
    private AdapterEnfermeiro adapter;
    private List<UsuarioModel> lista = new ArrayList<>();

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityVerEnfermeirosBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Enfermeiros");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mainBinding.rvEnfermerios.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rvEnfermerios.setHasFixedSize(true);
        mainBinding.rvEnfermerios.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new AdapterEnfermeiro(lista, this);
        mainBinding.rvEnfermerios.setAdapter(adapter);


        ref.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mainBinding.progressBar.setVisibility(View.VISIBLE);
                lista.clear();

                for ( DataSnapshot dado : snapshot.getChildren()){
                    UsuarioModel usuarioModel = dado.getValue(UsuarioModel.class);
                    lista.add(usuarioModel);
                }

                adapter.notifyDataSetChanged();
                mainBinding.progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}