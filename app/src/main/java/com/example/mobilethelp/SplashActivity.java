package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilethelp.util.SessionManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SessionManager sessionManager = new SessionManager(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Verifica se o token existe
            if (sessionManager.fetchAuthToken() != null) {
                // Usuário está logado, vai para a Home
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            } else {
                // Usuário não está logado, vai para o Login
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish(); // Finaliza a SplashActivity
        }, 1500); // Atraso de 1.5 segundos
    }
}