package com.example.mobilethelp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilethelp.adapter.ChatAdapter;
import com.example.mobilethelp.interfaces.ApiClient;
import com.example.mobilethelp.model.ChatMessage;
import com.example.mobilethelp.model.SendMessageRequest;
import com.example.mobilethelp.service.ChatService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChat;
    private ChatAdapter chatAdapter;
    private List<ChatMessage> messageList;
    private EditText editTextMessage;
    private ImageButton buttonSend;

    private ChatService chatService;
    private Long sessionId;
    private static final String TAG = "ChatActivity";
    // TODO: Idealmente, o nome do usuário viria do token decodificado ou de uma tela de perfil
    private static final String CURRENT_USER_NAME = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket); // Reutilizando o layout do ticket
        setTitle("Chat");

        sessionId = getIntent().getLongExtra("SESSION_ID", -1);
        if (sessionId == -1) {
            Toast.makeText(this, "Erro: ID da sessão não encontrado.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        chatService = ApiClient.getClient(this).create(ChatService.class);

        recyclerViewChat = findViewById(R.id.recyclerViewInteractions);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList, CURRENT_USER_NAME);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewChat.setAdapter(chatAdapter);

        buttonSend.setOnClickListener(v -> {
            String messageText = editTextMessage.getText().toString().trim();
            if (!messageText.isEmpty()) {
                sendMessage(messageText);
            }
        });

        loadChatMessages();
    }

    private void loadChatMessages() {
        Call<List<ChatMessage>> call = chatService.getChatMessages(sessionId);
        call.enqueue(new Callback<List<ChatMessage>>() {
            @Override
            public void onResponse(@NonNull Call<List<ChatMessage>> call, @NonNull Response<List<ChatMessage>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    messageList.clear();
                    messageList.addAll(response.body());
                    chatAdapter.notifyDataSetChanged();
                    recyclerViewChat.scrollToPosition(messageList.size() - 1);
                } else {
                    Toast.makeText(ChatActivity.this, "Falha ao carregar mensagens.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ChatMessage>> call, @NonNull Throwable t) {
                Toast.makeText(ChatActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendMessage(String messageText) {
        SendMessageRequest request = new SendMessageRequest(messageText);
        Call<ChatMessage> call = chatService.sendMessage(sessionId, request);

        call.enqueue(new Callback<ChatMessage>() {
            @Override
            public void onResponse(@NonNull Call<ChatMessage> call, @NonNull Response<ChatMessage> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Adiciona a nova mensagem à lista e notifica o adapter
                    messageList.add(response.body());
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerViewChat.scrollToPosition(messageList.size() - 1);
                    editTextMessage.setText(""); // Limpa o campo de texto
                } else {
                    Toast.makeText(ChatActivity.this, "Falha ao enviar mensagem.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ChatMessage> call, @NonNull Throwable t) {
                Toast.makeText(ChatActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}