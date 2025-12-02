package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.model.CreateChamadoRequest;
import com.example.mobilethelp.service.ChamadoService;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTicketActivity extends AppCompatActivity {

    private TextInputEditText editTextTitle, editTextDescription;
    private ChamadoService chamadoService;
    private static final String TAG = "CreateTicketActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // O botão de voltar não é necessário se esta é a primeira tela
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true); 

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        Button buttonCreateTicket = findViewById(R.id.buttonCreateTicket);

        buttonCreateTicket.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }
            createTicket(title, description);
        });
    }

    private void createTicket(String title, String description) {
        CreateChamadoRequest request = new CreateChamadoRequest(title, description);
        Call<Chamado> call = chamadoService.createChamado(request);

        call.enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(@NonNull Call<Chamado> call, @NonNull Response<Chamado> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateTicketActivity.this, "Chamado criado com sucesso!", Toast.LENGTH_SHORT).show();
                    
                    // Redireciona para a lista de chamados
                    Intent intent = new Intent(CreateTicketActivity.this, TicketListActivity.class);
                    // Limpa a tela de criação da pilha para que o usuário não volte para ela
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CreateTicketActivity.this, "Falha ao criar chamado.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Erro ao criar chamado, código: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chamado> call, @NonNull Throwable t) {
                Toast.makeText(CreateTicketActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Falha de rede ao criar chamado", t);
            }
        });
    }
}