package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.mobilethelp.util.SessionManager;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MaterialCardView cardViewMyChamados = findViewById(R.id.cardViewMyChamados);
        MaterialCardView cardViewCreateChamado = findViewById(R.id.cardViewCreateChamado);
        FloatingActionButton fabLogout = findViewById(R.id.fabLogout);

        // Ação para o card "Meus Chamados" -> abre a LISTA
        cardViewMyChamados.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ChamadoListActivity.class);
            startActivity(intent);
        });

        // Ação para o card "Criar Chamado" -> abre o FORMULÁRIO
        cardViewCreateChamado.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CreateChamadoActivity.class);
            startActivity(intent);
        });

        fabLogout.setOnClickListener(v -> {
            sessionManager.clearAuthToken();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}