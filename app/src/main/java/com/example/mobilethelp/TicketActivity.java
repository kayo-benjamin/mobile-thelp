package com.example.mobilethelp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class TicketActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInteractions;
    private InteracaoAdapter interacaoAdapter;
    private List<Interacao> interacaoList = new ArrayList<>();
    private EditText editTextMessage;
    private ChamadoService chamadoService;
    private Long chamadoId;
    private static final String TAG = "TicketActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        chamadoId = getIntent().getLongExtra("CHAMADO_ID", -1);
        if (chamadoId == -1) {
            Toast.makeText(this, "Erro: ID do Chamado não encontrado.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        
        recyclerViewInteractions = findViewById(R.id.recyclerViewInteractions);
        editTextMessage = findViewById(R.id.editTextMessage);
        ImageButton buttonSend = findViewById(R.id.buttonSend);
        
        setupRecyclerView();
        loadInteractions();

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                addInteraction(message);
            }
        });
    }

    private void setupRecyclerView() {
        interacaoAdapter = new InteracaoAdapter(interacaoList);
        recyclerViewInteractions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInteractions.setAdapter(interacaoAdapter);
    }

    private void loadInteractions() {
        Call<Chamado> call = chamadoService.getChamadoById(chamadoId);
        call.enqueue(new Callback<Chamado>() {
            @Override
            public void onResponse(@NonNull Call<Chamado> call, @NonNull Response<Chamado> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Chamado chamado = response.body();
                    setTitle("Chamado #" + chamado.getId());
                    interacaoList.clear();
                    if (chamado.getInteracoes() != null) {
                        interacaoList.addAll(chamado.getInteracoes());
                    }
                    interacaoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(TicketActivity.this, "Falha ao carregar interações.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chamado> call, @NonNull Throwable t) {
                Toast.makeText(TicketActivity.this, "Erro de rede.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addInteraction(String message) {
        AddInteracaoRequest request = new AddInteracaoRequest(message);
        Call<Interacao> call = chamadoService.addInteracao(chamadoId, request);
        call.enqueue(new Callback<Interacao>() {
            @Override
            public void onResponse(@NonNull Call<Interacao> call, @NonNull Response<Interacao> response) {
                if (response.isSuccessful() && response.body() != null) {
                    interacaoList.add(response.body());
                    interacaoAdapter.notifyItemInserted(interacaoList.size() - 1);
                    recyclerViewInteractions.scrollToPosition(interacaoList.size() - 1);
                    editTextMessage.setText("");
                } else {
                    Toast.makeText(TicketActivity.this, "Falha ao enviar mensagem.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Interacao> call, @NonNull Throwable t) {
                Toast.makeText(TicketActivity.this, "Erro de rede.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}