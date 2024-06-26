package benicio.solucoes.palienf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import benicio.solucoes.palienf.databinding.ActivityLoginBinding;
import benicio.solucoes.palienf.databinding.ActivityMenuBinding;

public class MenuActivity extends AppCompatActivity {

    private ActivityMenuBinding mainBinding;
    private Bundle b;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Menu");

        b = getIntent().getExtras();

        if ( b != null){
            if ( b.getBoolean("admin") ){
                mainBinding.cadastrarUsuario.setVisibility(View.VISIBLE);
                mainBinding.verUsuario.setVisibility(View.VISIBLE);
            }
        }

        mainBinding.verPacientes.setOnClickListener(view -> startActivity(new Intent(this, ListarPacienteActivity.class)));
        mainBinding.cadastrarPaciente.setOnClickListener(view -> startActivity(new Intent(this, NovoPacienteActivity.class)));
        mainBinding.relatarProblema.setOnClickListener(view -> startActivity(new Intent(this, RelatarProblemaActivity.class)));
        mainBinding.configuracoes.setOnClickListener(view ->  startActivity(new Intent(this, ConfiguracoesActivity.class)));
        mainBinding.cadastrarUsuario.setOnClickListener(view -> startActivity(new Intent(this, CadastrarUsuarioActivity.class)));
        mainBinding.verUsuario.setOnClickListener(view -> startActivity(new Intent(this, VerEnfermeirosActivity.class)));
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mainBinding.deslogar.setOnClickListener(v -> {
            editor.remove("id").apply();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}