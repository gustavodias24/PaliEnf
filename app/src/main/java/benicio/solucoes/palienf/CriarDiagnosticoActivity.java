package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import benicio.solucoes.palienf.databinding.ActivityCriarDiagnosticoBinding;

public class CriarDiagnosticoActivity extends AppCompatActivity {

    private ActivityCriarDiagnosticoBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCriarDiagnosticoBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_criar_diagnostico);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Domínios");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = new Intent(this, DiagnosticoSelecaoActivity.class);

        mainBinding.d1.setOnClickListener(view -> {
            i.putExtra("d", 1);
            startActivity(i);
        });
        mainBinding.d2.setOnClickListener(view -> {
            i.putExtra("d", 2);
            startActivity(i);
        });
        mainBinding.d3.setOnClickListener(view -> {
            i.putExtra("d", 3);
            startActivity(i);
        });
        mainBinding.d4.setOnClickListener(view -> {
            i.putExtra("d", 4);
            startActivity(i);
        });
        mainBinding.d5.setOnClickListener(view -> {
            i.putExtra("d", 5);
            startActivity(i);
        });
        mainBinding.d6.setOnClickListener(view -> {
            i.putExtra("d", 6);
            startActivity(i);
        });
        mainBinding.d7.setOnClickListener(view -> {
            i.putExtra("d", 7);
            startActivity(i);
        });
        mainBinding.d8.setOnClickListener(view -> {
            i.putExtra("d", 8);
            startActivity(i);
        });
        mainBinding.d9.setOnClickListener(view -> {
            i.putExtra("d", 9);
            startActivity(i);
        });
        mainBinding.d10.setOnClickListener(view -> {
            i.putExtra("d", 10);
            startActivity(i);
        });
        mainBinding.d11.setOnClickListener(view -> {
            i.putExtra("d", 11);
            startActivity(i);
        });
        mainBinding.d12.setOnClickListener(view -> {
            i.putExtra("d", 12);
            startActivity(i);
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