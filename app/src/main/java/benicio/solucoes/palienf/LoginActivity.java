package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import benicio.solucoes.palienf.databinding.ActivityLoginBinding;
import benicio.solucoes.palienf.model.UsuarioModel;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mainBinding;
    private DatabaseReference refUsuarios = FirebaseDatabase.getInstance().getReference().child("usuarios");
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mainBinding.verSenha.setOnClickListener(view -> mainBinding.edtSenha.setInputType(InputType.TYPE_CLASS_TEXT));

        mainBinding.btnEntrar.setOnClickListener(view -> {
            String user, senha;

            user = mainBinding.edtUser.getText().toString().trim().toLowerCase();
            senha = mainBinding.edtSenha.getText().toString().trim().toLowerCase();

            if ( user.equals("administrador") && senha.equals("administrador") ){
                startActivity(new Intent(this, MenuActivity.class));
            }else{
                refUsuarios.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        boolean logado = false;
                        for (DataSnapshot dado : snapshot.getChildren()){
                            UsuarioModel usuarioBD = dado.getValue(UsuarioModel.class);

                            if ( usuarioBD.getNr().trim().toLowerCase().equals(user)){
                                if( usuarioBD.getSenha().trim().toLowerCase().equals(senha)){
                                    startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                                    logado = true;
                                    editor.putString("id", usuarioBD.getId()).apply();
                                    break;
                                }
                            }
                        }
                        if ( !logado ){
                            Toast.makeText(LoginActivity.this, "Credenciais Erradas.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}