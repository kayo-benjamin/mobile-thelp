package com.example.mobilethelp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.adapter.InteracaoAdapter;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.AddInteracaoRequest;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.model.Interacao;
import com.example.mobilethelp.service.ChamadoService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChamadoDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInteractions;
    private InteracaoAdapter interacaoAdapter;
    private List<Interacao> interacaoList = new ArrayList<>();
    private EditText editTextMessage;
    private ChamadoService chamadoService;
    private Integer chamadoId;
    private Integer currentUserId = 1; // TODO: Obter o ID do usuário real do SessionManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_detail);

        chamadoId = getIntent().getIntExtra("CHAMADO_ID", -1);
        if (chamadoId == -1) {
            finish();
            return;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        
        recyclerViewInteractions = findViewById(R.id.recyclerViewInteractions);
        editTextMessage = findViewById(R.id.editTextMessage);
        Button buttonSend = findViewById(R.id.buttonSend);
        Button buttonCloseTicket = findViewById(R.id.buttonCloseTicket);
        
        setupRecyclerView();
        loadChamadoDetails();

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                addInteraction(message);
            }
        });
        
        buttonCloseTicket.setOnClickListener(v -> closeChamado());
    }

    private void setupRecyclerView() {
        // Passa o ID do usuário logado para o adapter
        interacaoAdapter = new InteracaoAdapter(interacaoList, currentUserId);
        recyclerViewInteractions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInteractions.setAdapter(interacaoAdapter);
    }

    private void loadChamadoDetails() {
        chamadoService.getChamadoById(chamadoId).enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(@NonNull Call<Chamado> call, @NonNull Response<Chamado> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Chamado chamado = response.body();
                    getSupportActionBar().setTitle("Chamado #" + chamado.getId());
                    interacaoList.clear();
                    if (chamado.getInteracoes() != null) {
                        interacaoList.addAll(chamado.getInteracoes());
                    }
                    interacaoAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Chamado> call, @NonNull Throwable t) {}
        });
    }

    private void addInteraction(String message) {
        chamadoService.addInteracao(chamadoId, new AddInteracaoRequest(message)).enqueue(new Callback<Interacao>() {
            @Override
            public void onResponse(@NonNull Call<Interacao> call, @NonNull Response<Interacao> response) {
                if (response.isSuccessful() && response.body() != null) {
                    interacaoList.add(response.body());
                    interacaoAdapter.notifyItemInserted(interacaoList.size() - 1);
                    recyclerViewInteractions.scrollToPosition(interacaoList.size() - 1);
                    editTextMessage.setText("");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Interacao> call, @NonNull Throwable t) {}
        });
    }
    
    private void closeChamado() {
        chamadoService.fecharChamado(chamadoId).enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(@NonNull Call<Chamado> call, @NonNull Response<Chamado> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ChamadoDetailActivity.this, "Chamado fechado com sucesso", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
            @Override
            public void onFailure(@NonNull Call<Chamado> call, @NonNull Throwable t) {}
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}