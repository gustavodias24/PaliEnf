package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import benicio.solucoes.palienf.databinding.ActivityCadastrarUsuarioBinding;
import benicio.solucoes.palienf.databinding.ActivityMenuBinding;
import benicio.solucoes.palienf.model.UsuarioModel;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private ActivityCadastrarUsuarioBinding mainBinding;
    private DatabaseReference refUsuarios = FirebaseDatabase.getInstance().getReference().child("usuarios");
    private Bundle b;
    private UsuarioModel enfermeiro = new UsuarioModel();
    private boolean edicao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityCadastrarUsuarioBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        b = getIntent().getExtras();
        if (b != null) {
            enfermeiro = b.getSerializable("payloadEdicao", UsuarioModel.class);
            edicao = true;

            mainBinding.email.setText(enfermeiro.getEmail());
            mainBinding.nr.setText(enfermeiro.getNr());
            mainBinding.nome.setText(enfermeiro.getNome());
            mainBinding.senha.setText(enfermeiro.getSenha());

            mainBinding.cadastrar.setText("Atualizar");
        }

        getSupportActionBar().setTitle(edicao ? "Atualizar Enfermeiro(a)" : "Cadastrar Novo Enfermeiro(a)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        mainBinding.cadastrar.setOnClickListener(view -> {
            String email = mainBinding.email.getText().toString();
            String nr = mainBinding.nr.getText().toString();
            String nome = mainBinding.nome.getText().toString();
            String senha = mainBinding.senha.getText().toString();


            if (email.isEmpty() || nr.isEmpty() || nome.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha todas as informações", Toast.LENGTH_SHORT).show();
            } else {

                if (nr.length() != 7) {
                    Toast.makeText(this, "Número de registro precisa ter 7 dígitos.", Toast.LENGTH_SHORT).show();
                } else {

                    String id = !edicao ? Base64.encodeToString(email.getBytes(), Base64.DEFAULT).trim() : enfermeiro.getId();

                    enfermeiro.setId(id);
                    enfermeiro.setNome(nome);
                    enfermeiro.setEmail(email);
                    enfermeiro.setNr(nr);
                    enfermeiro.setSenha(senha);


                    refUsuarios.child(enfermeiro.getId()).setValue(enfermeiro).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            if (!edicao) {
                                Toast.makeText(this, "Cadastro Realizado", Toast.LENGTH_LONG).show();
                                mainBinding.email.setText("");
                                mainBinding.nr.setText("");
                                mainBinding.nome.setText("");
                                mainBinding.senha.setText("");
                            } else {
                                Toast.makeText(this, "Atualização Realizada", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
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