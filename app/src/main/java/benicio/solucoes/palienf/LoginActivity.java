package benicio.solucoes.palienf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import benicio.solucoes.palienf.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mainBinding.btnEntrar.setOnClickListener(view -> {
            String user, senha;

            user = mainBinding.edtUser.getText().toString().trim().toLowerCase();
            senha = mainBinding.edtSenha.getText().toString().trim().toLowerCase();

            if ( user.equals("administrador") && senha.equals("administrador") ){
                startActivity(new Intent(this, MenuActivity.class));
            }else{
                Toast.makeText(this, "Credenciais Erradas.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}