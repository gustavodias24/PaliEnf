package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import benicio.solucoes.palienf.databinding.ActivityCadastrarUsuarioBinding;
import benicio.solucoes.palienf.databinding.ActivityConfiguracoesBinding;
import benicio.solucoes.palienf.model.UsuarioModel;

public class ConfiguracoesActivity extends AppCompatActivity {
    
    private ActivityConfiguracoesBinding mainBinding;
    private DatabaseReference refUsuarios = FirebaseDatabase.getInstance().getReference().child("usuarios");
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityConfiguracoesBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Configurações");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        
        mainBinding.cadastrar.setOnClickListener( view -> {

            refUsuarios.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UsuarioModel userLogado = null;
                    for ( DataSnapshot dado : snapshot.getChildren()){
                        UsuarioModel usuarioDb = dado.getValue(UsuarioModel.class);
                        if ( usuarioDb.getId().equals(sharedPreferences.getString("id", ""))){
                            userLogado = usuarioDb;
                            break;
                        }
                    }
                    String novaSenha = mainBinding.novaSenha.getText().toString();
                    String antigaSenha = mainBinding.senhaAntiga.getText().toString();

                    if ( !antigaSenha.isEmpty()){
                        if ( antigaSenha.equals(userLogado.getSenha())){
                            if ( !novaSenha.isEmpty() ){
                                userLogado.setSenha(novaSenha);
                                refUsuarios.child(sharedPreferences.getString("id", "")).setValue(userLogado).addOnCompleteListener(task -> {
                                    if ( task.isSuccessful()){
                                        mainBinding.repetir.setText("");
                                        mainBinding.novaSenha.setText("");
                                        mainBinding.senhaAntiga.setText("");
                                        Toast.makeText(ConfiguracoesActivity.this, "Senha trocada com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }else{
                            Toast.makeText(ConfiguracoesActivity.this, "Senha antiga incorreta!", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }
}