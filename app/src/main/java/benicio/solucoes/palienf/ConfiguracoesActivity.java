package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import benicio.solucoes.palienf.databinding.ActivityCadastrarUsuarioBinding;
import benicio.solucoes.palienf.databinding.ActivityConfiguracoesBinding;

public class ConfiguracoesActivity extends AppCompatActivity {
    
    private ActivityConfiguracoesBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Configurações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        
        mainBinding.cadastrar.setOnClickListener( view -> Toast.makeText(this, "Senha trocada com sucesso!", Toast.LENGTH_SHORT).show());
        
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }
}