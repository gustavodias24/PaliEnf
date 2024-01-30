package benicio.solucoes.palienf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import benicio.solucoes.palienf.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000;

    private ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Animação de fade in
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1500); // 1,5 segundos
        mainBinding.imageView.startAnimation(fadeIn);

        // Inicia a próxima atividade após 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent;
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }
}