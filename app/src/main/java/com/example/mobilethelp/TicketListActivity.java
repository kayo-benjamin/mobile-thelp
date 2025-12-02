package com.example.mobilethelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.adapter.TicketAdapter;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.service.ChamadoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTickets;
    private TicketAdapter ticketAdapter;
    private List<Chamado> chamadoList = new ArrayList<>();
    private ChamadoService chamadoService;
    private static final String TAG = "TicketListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        recyclerViewTickets = findViewById(R.id.recyclerViewTickets);
        setupRecyclerView();

        FloatingActionButton fabAddTicket = findViewById(R.id.fabAddTicket);
        fabAddTicket.setOnClickListener(v -> {
            // Abre a tela de criação
            Intent intent = new Intent(TicketListActivity.this, CreateTicketActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChamados(); // Recarrega a lista sempre que a tela se torna visível
    }

    private void setupRecyclerView() {
        ticketAdapter = new TicketAdapter(chamadoList, chamado -> {
            // Ação de clique: abrir a tela de detalhes
            Intent intent = new Intent(TicketListActivity.this, TicketActivity.class);
            intent.putExtra("CHAMADO_ID", chamado.getId());
            startActivity(intent);
        });
        recyclerViewTickets.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTickets.setAdapter(ticketAdapter);
    }

    private void loadChamados() {
        Call<List<Chamado>> call = chamadoService.getChamados();
        call.enqueue(new Callback<List<Chamado>>() {
            @Override
            public void onResponse(@NonNull Call<List<Chamado>> call, @NonNull Response<List<Chamado>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chamadoList.clear();
                    chamadoList.addAll(response.body());
                    ticketAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(TicketListActivity.this, "Falha ao carregar chamados.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Erro ao carregar chamados, código: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Chamado>> call, @NonNull Throwable t) {
                Toast.makeText(TicketListActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Falha de rede ao carregar chamados", t);
            }
        });
    }
}