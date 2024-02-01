package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.MenuItem;

import benicio.solucoes.palienf.databinding.ActivityConfiguracoesBinding;
import benicio.solucoes.palienf.databinding.ActivityRelatarProblemaBinding;

public class RelatarProblemaActivity extends AppCompatActivity {

    private ActivityRelatarProblemaBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityRelatarProblemaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }
}