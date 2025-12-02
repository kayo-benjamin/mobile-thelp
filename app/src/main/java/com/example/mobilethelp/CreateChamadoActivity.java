package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.model.Organizacao;
import com.example.mobilethelp.model.Usuario;
import com.example.mobilethelp.service.ChamadoService;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateChamadoActivity extends AppCompatActivity {

    private TextInputEditText editTextTitle, editTextDescription;
    private ChamadoService chamadoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_chamado);
        
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        Button buttonCreate = findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }
            createChamado(title, description);
        });
    }

    private void createChamado(String title, String description) {
        // Cria os objetos internos
        Organizacao org = new Organizacao(1); // TODO: Obter ID da organização do usuário
        Usuario user = new Usuario(1); // TODO: Obter ID do usuário logado

        // Cria o objeto Chamado completo
        Chamado novoChamado = new Chamado();
        novoChamado.setTitulo(title);
        novoChamado.setDescricao(description);
        novoChamado.setIdOrganizacao(org);
        novoChamado.setIdUsuarioAbertura(user);

        Call<Chamado> call = chamadoService.createChamado(novoChamado);
        call.enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(@NonNull Call<Chamado> call, @NonNull Response<Chamado> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateChamadoActivity.this, "Chamado criado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreateChamadoActivity.this, "Falha ao criar chamado. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chamado> call, @NonNull Throwable t) {
                Toast.makeText(CreateChamadoActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}