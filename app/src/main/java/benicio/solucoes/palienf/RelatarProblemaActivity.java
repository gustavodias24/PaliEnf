package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

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

        mainBinding.enviarEmail.setOnClickListener(view -> {
            String conteudo = mainBinding.conteudoEmail.getText().toString();
            if ( conteudo.isEmpty()){
                Toast.makeText(this, "Digite Algo!", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:palienfsaude@gmail.com")); // Apenas apps de email devem manusear isso
                intent.putExtra(Intent.EXTRA_SUBJECT, "Comentário Sobre Palienf");
                intent.putExtra(Intent.EXTRA_TEXT, conteudo);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == android.R.id.home) { finish();}
        return super.onOptionsItemSelected(item);
    }
}