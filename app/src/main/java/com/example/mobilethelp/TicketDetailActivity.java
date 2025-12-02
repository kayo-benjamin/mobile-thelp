package com.example.mobilethelp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.adapter.InteractionAdapter;
import com.example.mobilethelp.model.Interacao;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TicketDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerViewInteractions;
    private InteractionAdapter interactionAdapter;
    private List<Interacao> interactionList;
    private EditText editTextMessage;
    private ImageButton buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        recyclerViewInteractions = findViewById(R.id.recyclerViewInteractions);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        interactionList = new ArrayList<>();
        interactionAdapter = new InteractionAdapter(interactionList); // Agora está correto

        recyclerViewInteractions.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewInteractions.setAdapter(interactionAdapter);

        loadDummyInteractions();

        buttonSend.setOnClickListener(v -> {
            String message = editTextMessage.getText().toString().trim();
            if (!message.isEmpty()) {
                addInteraction("User", message);
                editTextMessage.setText("");
            }
        });
    }

    private void loadDummyInteractions() {
        interactionList.add(new Interacao("Support", "Hello! How can I help you today?", "10:00 AM"));
        interactionList.add(new Interacao("User", "I'm having an issue with...", "10:05 AM"));
        interactionAdapter.notifyDataSetChanged(); // Descomentado
    }

    private void addInteraction(String author, String message) {
        String timestamp = new SimpleDateFormat("HH:mm a", Locale.getDefault()).format(new Date());
        interactionList.add(new Interacao(author, message, timestamp));
        interactionAdapter.notifyItemInserted(interactionList.size() - 1);
        recyclerViewInteractions.scrollToPosition(interactionList.size() - 1);
    }
}