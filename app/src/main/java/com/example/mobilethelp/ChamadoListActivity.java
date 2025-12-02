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
import com.example.mobilethelp.adapter.ChamadoAdapter;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.Chamado;
import com.example.mobilethelp.service.ChamadoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChamadoListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChamados;
    private ChamadoAdapter chamadoAdapter;
    private List<Chamado> chamadoList = new ArrayList<>();
    private ChamadoService chamadoService;
    private static final String TAG = "ChamadoListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_list); // Novo layout

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chamadoService = ApiClient.getClient(this).create(ChamadoService.class);
        recyclerViewChamados = findViewById(R.id.recyclerViewChamados);
        setupRecyclerView();

        FloatingActionButton fabAddChamado = findViewById(R.id.fabAddChamado);
        fabAddChamado.setOnClickListener(v -> {
            Intent intent = new Intent(ChamadoListActivity.this, CreateChamadoActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChamados();
    }

    private void setupRecyclerView() {
        chamadoAdapter = new ChamadoAdapter(chamadoList, chamado -> {
            Intent intent = new Intent(ChamadoListActivity.this, ChamadoDetailActivity.class);
            intent.putExtra("CHAMADO_ID", chamado.getId());
            startActivity(intent);
        });
        recyclerViewChamados.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChamados.setAdapter(chamadoAdapter);
    }

    private void loadChamados() {
        Call<List<Chamado>> call = chamadoService.getChamados();
        call.enqueue(new Callback<List<Chamado>>() {
            @Override
            public void onResponse(@NonNull Call<List<Chamado>> call, @NonNull Response<List<Chamado>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    chamadoList.clear();
                    chamadoList.addAll(response.body());
                    chamadoAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ChamadoListActivity.this, "Falha ao carregar chamados.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Chamado>> call, @NonNull Throwable t) {
                Toast.makeText(ChamadoListActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}